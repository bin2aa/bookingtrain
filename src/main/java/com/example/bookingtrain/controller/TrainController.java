package com.example.bookingtrain.controller;

import com.example.bookingtrain.model.Train;
import com.example.bookingtrain.service.TrainService;
import com.example.bookingtrain.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public String showTrains(Model model) {
        List<Train> listTrain = trainService.getAll();
        model.addAttribute("listTrain", listTrain);
        return "list/trainList";
    }

    @GetMapping("/newTrain")
    public String showAddPage(Model model) {
        // Truyen model moi vao create form
        model.addAttribute("train", new Train());
        return "add/addTrain";
    }

    @PostMapping("/save")
    public String saveTrain(@RequestParam(name="imageFile") MultipartFile multipartFile,
            @ModelAttribute("train") Train train) throws IOException {
        // Lay file goc
        String fileURL = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        train.setImage(fileURL);
        Train temp = trainService.create(train);

        String uploadDir = "static/images/trainPhotos/" + temp.getTrainId();
        FileUploadUtil.saveFile(uploadDir, fileURL, multipartFile);

        return "redirect:/trains";
    }

    @GetMapping("/edit/{trainID}")
    public String showEditPage(@PathVariable(name = "trainID") long trainID, Model model) {
        // Tim train bang duong dan
        Train train = trainService.getById(trainID);
        model.addAttribute("train", train);
        // Show edit page
        return "edit/trainEdit";
    }

    @GetMapping("/delete/{trainID}")
    public String deleteTrain(@PathVariable(name = "trainID") long trainID) {
        trainService.delete(trainID);
        return "redirect:/trains";
    }
}
