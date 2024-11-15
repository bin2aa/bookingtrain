package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Train;
import com.example.bookingtrain.service.TrainService;
import com.example.bookingtrain.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/trains")
public class TrainController {

    private final TrainService trainService;

    @Autowired
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping("")
    public String showList(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 1;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("trainId").ascending());
        Page<Train> trainPage = trainService.getAllTrains(pageable); // Lấy danh sách trains từ service

        model.addAttribute("trainList", trainPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", trainPage.getTotalPages());
        model.addAttribute("baseUrl", "/trains");
        return "list/trainList";
    }

    @GetMapping("/addTrain")
    public String showAddForm(Model model) {
        model.addAttribute("train", new Train());
        return "add/addTrain";
    }

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
        return "redirect:/trains";
    }

    @GetMapping("/editTrain/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Train train = trainService.getById(id);
        if (train != null) {
            model.addAttribute("train", train);
            return "edit/editTrain";
        }
        return "redirect:/trains";
    }

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

    @GetMapping("/deleteTrain/{id}")
    public String deleteTrain(@PathVariable("id") Integer id) {
        trainService.delete(id);
        return "redirect:/trains";
    }
}