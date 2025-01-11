package com.example.bookingtrain.controller;

import com.example.bookingtrain.DTO.TrainScheduleDTO;
import com.example.bookingtrain.service.StationService;
import com.example.bookingtrain.model.Train;
import com.example.bookingtrain.service.TrainService;
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
@RequestMapping("/trains")
public class TrainController {

    private final TrainService trainService;

    private final StationService stationService;

    @Autowired
    public TrainController(TrainService trainService, StationService stationService) {
        this.trainService = trainService;

        this.stationService = stationService;
    }

    @PreAuthorize("@roleOperationService.hasPermission(authentication, 'trains', 1)")
    @GetMapping("")
    public String showList(@RequestParam(defaultValue = "0") int page, @RequestParam(required = false) String search,
            Model model) {
        int pageSize = 8;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("trainId").ascending());
        Page<Train> trainPage;
        if (search != null && !search.isEmpty()) {
            trainPage = trainService.searchTrainsByName(search, pageable);
        } else {
            trainPage = trainService.getAllTrains(pageable);
        }
        model.addAttribute("trainList", trainPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", trainPage.getTotalPages());
        model.addAttribute("baseUrl", "/trains");
        model.addAttribute("search", search);
        return "list/trainList";
    }

    @PreAuthorize("@roleOperationService.hasPermission(authentication, 'trains', 2)")
    @GetMapping("/addTrain")
    public String showAddForm(Model model) {
        model.addAttribute("train", new Train());
        return "add/addTrain";
    }

    @PreAuthorize("@roleOperationService.hasPermission(authentication, 'trains', 2)")
    @PostMapping("/saveTrain")
    public String saveTrain(@ModelAttribute("train") Train train,
            @RequestParam("imageFile") MultipartFile multipartFile,
            Model model) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            train.setImage(fileName);

            String uploadDir = "img/trainImg/";
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("train", train);
            return "add/addTrain";
        }
        trainService.save(train);
        return "redirect:/trains";
    }

    @PreAuthorize("@roleOperationService.hasPermission(authentication, 'trains', 4)")
    @GetMapping("/editTrain/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Train train = trainService.getById(id);
        if (train != null) {
            model.addAttribute("train", train);
            return "edit/editTrain";
        }
        return "redirect:/trains";
    }

    @PreAuthorize("@roleOperationService.hasPermission(authentication, 'trains', 4)")
    @PostMapping("/editJson")
    @ResponseBody
    public String editTrainStatus(@RequestBody Map<String, Integer> payload) {
        Integer trainId = payload.get("trainId");
        Integer statusTrain = payload.get("statusTrain");

        Train train = trainService.getById(trainId);
        if (train == null) {
            return "error";
        }
        train.setStatusTrain(statusTrain);
        trainService.update(trainId, train);
        return "success";
    }

    @PreAuthorize("@roleOperationService.hasPermission(authentication, 'trains', 4)")
    @PostMapping("/updateTrain")
    public String updateTrain(@ModelAttribute("train") Train train,
            @RequestParam("imageFile") MultipartFile multipartFile,
            Model model) {
        try {
            // Lấy thông tin tàu hiện tại từ cơ sở dữ liệu
            Train existingTrain = trainService.getById(train.getTrainId());
            if (existingTrain == null) {
                return "redirect:/trains";
            }

            // Nếu người dùng không chọn ảnh mới thì giữ nguyên ảnh cũ
            if (!multipartFile.isEmpty()) {
                String fileName = multipartFile.getOriginalFilename();
                train.setImage(fileName);

                String uploadDir = "img/trainImg/";
                FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            } else {
                train.setImage(existingTrain.getImage());
            }

            trainService.update(train.getTrainId(), train);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("train", train);
            return "edit/editTrain";
        }
        return "redirect:/trains";
    }

    @PreAuthorize("@roleOperationService.hasPermission(authentication, 'trains', 3)")
    @GetMapping("/deleteTrain/{id}")
    public String deleteTrain(@PathVariable("id") Integer id) {
        trainService.delete(id);
        return "redirect:/trains";
    }

    // @GetMapping("/client/train")
    // public String showTrainList(Model model) {
    // List<TrainScheduleDTO> trainSchedules = stationService.getTrainSchedules();
    // model.addAttribute("trainSchedules", trainSchedules);
    // return "Client/Components/Train";
    // }
}