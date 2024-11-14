package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Station;
import com.example.bookingtrain.model.Train;
import com.example.bookingtrain.service.StationService;
import com.example.bookingtrain.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/stations")
public class StationController {

    private final StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("")
    public String showList(Model model) {
        List<Station> stationList = stationService.getAll();
        model.addAttribute("stationList", stationList);
        return "list/stationList";
    }

    @GetMapping("/addStation")
    public String showAddForm(Model model) {
        model.addAttribute("station", new Station());
        return "add/addStation";
    }

    @PostMapping("/saveStation")
    public String saveStation(@ModelAttribute("station") Station station,
            @RequestParam("imageFile") MultipartFile multipartFile,
            Model model) {
        try {
            Station existingStation = stationService.getById(station.getStationId());
            if (existingStation == null) {
                return "redirect:/stations";
            }
            if (!multipartFile.isEmpty()) {
                String fileName = multipartFile.getOriginalFilename();
                station.setImage(fileName);

                String uploadDir = "img/stationImg/";
                FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

            } else {
                station.setImage(existingStation.getImage());
            }
            // Cập nhật thông tin trạm
            stationService.updateStation(station.getStationId(), station);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("station", station);
            return "add/addStation";
        }
        return "redirect:/stations";
    }

    @GetMapping("/editStation/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Station station = stationService.getById(id);
        if (station != null) {
            model.addAttribute("station", station);
            return "edit/editStation";
        }
        return "redirect:/stations";
    }

    @PostMapping("/updateStation")
    public String updateStation(@ModelAttribute("station") Station station,
            @RequestParam("imageFile") MultipartFile multipartFile,
            Model model) {
        try {
            if (!multipartFile.isEmpty()) {
                String fileName = multipartFile.getOriginalFilename();
                station.setImage(fileName);

                String uploadDir = "img/stationImg/";
                FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            }

            stationService.updateStation(station.getStationId(), station);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("station", station);
            return "edit/editStation";
        }
        return "redirect:/stations";
    }

    @GetMapping("/deleteStation/{id}")
    public String deleteStation(@PathVariable("id") Integer id) {
        stationService.deleteStation(id);
        return "redirect:/stations";
    }
}