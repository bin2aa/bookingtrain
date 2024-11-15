 package com.example.bookingtrain.config;

 import org.springframework.context.annotation.Configuration;
 import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
 import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

 import java.nio.file.Path;
 import java.nio.file.Paths;

 @Configuration
 public class WebConfig implements WebMvcConfigurer {

     @Override
     public void addResourceHandlers(ResourceHandlerRegistry registry) {
         exposeDirectory("/static/images/trainImg", registry);
//         registry.addResourceHandler("/**")
//                 .addResourceLocations("classpath:/static/","classpath:/images/")
//                 .setCachePeriod(0);
     }


     private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
         Path uploadDir = Paths.get(dirName);
         String uploadPath = uploadDir.toFile().getAbsolutePath();

         if (dirName.startsWith("../")) dirName = dirName.replace("../", "");

         registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/"+ uploadPath + "/");
     }
 }