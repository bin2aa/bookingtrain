package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Train;
import com.example.bookingtrain.service.TrainService;
import com.example.bookingtrain.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/trains")
public class TrainController {

    private TrainService trainService;

    @Autowired
    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping("")
    public String showList(Model model) {
        List<Train> trainList = trainService.getAllTrains();
        model.addAttribute("trainList", trainList);
        return "list/trainList";
    }

    @GetMapping("/addTrain")
    public String showAddForm(Model model) {
        model.addAttribute("train", new Train());
        return "add/addTrain";
    }

    @PostMapping("/saveTrain")
    public String saveTrain (@ModelAttribute("train") Train train,
                             @RequestParam("imageFile") MultipartFile multipartFile,
                             Model model){
        if(train.getTrainId() != null) {
            trainService.save(train);
        }
        else{
            train.setStatusTrain(1);
            try {
                String fileName = multipartFile.getOriginalFilename();
                train.setImage(fileName);

                Train savedTrain = trainService.save(train);

                String uploadDir = "/static/media/img/trainImg/" + savedTrain.getTrainId() + "/";

                FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            }catch (IOException e){
                e.printStackTrace();
                model.addAttribute("train",train);
                return "add/addTrain";
            }
        }
        return "redirect:/trains";
    }

    @GetMapping("/editTrain/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Train train = trainService.getById(id);
        if(train != null){
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
