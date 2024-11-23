package com.example.bookingtrain.controller;

import com.example.bookingtrain.DTO.TrainScheduleDTO;
import com.example.bookingtrain.model.Station;
import com.example.bookingtrain.model.Train;
import com.example.bookingtrain.service.StationService;
import com.example.bookingtrain.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/stations")
public class StationController {

    private final StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("")
    public String showList(@RequestParam(defaultValue = "0") int page, @RequestParam(required = false) String search,
            Model model) {
        int pageSize = 8;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("stationId").ascending());
        Page<Station> stationPage;
        if (search != null && !search.isEmpty()) {
            stationPage = stationService.searchStationsByName(search, pageable);
        } else {
            stationPage = stationService.getAllStations(pageable);
        }

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", stationPage.getTotalPages());
        model.addAttribute("baseUrl", "/stations");
        // List<Station> stationList = stationService.getAll();
        model.addAttribute("stationList", stationPage);
        model.addAttribute("search", search);
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
            if (!multipartFile.isEmpty()) {
                String fileName = multipartFile.getOriginalFilename();
                station.setImage(fileName);

                String uploadDir = "img/stationImg/";
                FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            }
            // Tạo mới trạm
            stationService.createStation(station);
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

    @PostMapping("/editJson")
    @ResponseBody
    public String editStationStatus(@RequestBody Map<String, Integer> payload) {
        Integer stationId = payload.get("stationId");
        Integer statusStation = payload.get("statusStation");

        Station station = stationService.getById(stationId);
        if (station == null) {
            return "error";
        }
        station.setStatusStation(statusStation);
        stationService.updateStation(stationId, station);
        return "success";
    }

    @PostMapping("/updateStation")
    public String updateStation(@ModelAttribute("station") Station station,
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

    // Ai dang nhap duoc thi vo duoc
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/client/stations")
    public String showStationList(Model model) {
        List<Station> stations = stationService.getAll();
        model.addAttribute("stations", stations);
        return "Client/Components/Station";
    }

}