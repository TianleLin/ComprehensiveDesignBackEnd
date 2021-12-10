package com.example.springbootproject.controller;

import com.example.springbootproject.model.OneLevelMenu;
import com.example.springbootproject.model.ThreeLevelMenu;
import com.example.springbootproject.model.TwoLevelMenu;
import com.example.springbootproject.model.User;
import com.example.springbootproject.service.FileService;
import com.example.springbootproject.service.MenuService;
import com.example.springbootproject.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Api
//@CrossOrigin
@RequestMapping("/api")
public class MenuController {

    @Autowired
    FileService fileService;
    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;

    @PostMapping("/createOneLevelMenu")
    public ResponseEntity<OneLevelMenu> createOneLevelMenu(
            @RequestParam(name = "userID") Long userID,
//            @RequestParam(name = "index") String index,
            @RequestParam(name = "title") String title,
            HttpServletRequest request){
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                User user = userService.findUser(userID);
//                OneLevelMenu oneLevelMenu = menuService.findOneLevelMenu(index);
                if (user != null) {
                    if (user.isTeacher()) {
//                        if (oneLevelMenu == null) {
                            OneLevelMenu _oneLevelMenu = menuService.saveOneLevelMenu(new OneLevelMenu(title));
                            return new ResponseEntity<>(_oneLevelMenu, HttpStatus.OK);
//                        } else {
//                            return new ResponseEntity<>(null, HttpStatus.ALREADY_REPORTED);
//                        }
                    } else {
                        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
                    }
                } else {
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
            } else {
                httpSession.invalidate();
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/deleteOneLevelMenu")
    public ResponseEntity<OneLevelMenu> deleteOneLevelMenu(
            @RequestParam(name = "userID") Long userID,
            @RequestParam(name = "oneLevelMenuID") Long oneLevelMenuID,
            HttpServletRequest request
    ) {
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                User user = userService.findUser(userID);
                OneLevelMenu oneLevelMenu = menuService.findOneLevelMenu(oneLevelMenuID);
                if (user != null && oneLevelMenu != null) {
                    if (user.isTeacher()) {
                        if (menuService.deleteOneLevelMenu(oneLevelMenu)) {
                            return new ResponseEntity<>(oneLevelMenu, HttpStatus.OK);
                        } else {
                            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    } else {
                        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
                    }
                } else {
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
            } else {
                httpSession.invalidate();
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/editOneLevelMenu")
    public ResponseEntity<OneLevelMenu> editOneLevelMenu(
            @RequestParam(name = "userID") Long userID,
            @RequestParam(name = "oneLevelMenuID")Long oneLevelMenuID,
            @RequestParam(name = "title")String title,
            HttpServletRequest request
    ) {
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                User user = userService.findUser(userID);
                OneLevelMenu oneLevelMenu = menuService.findOneLevelMenu(oneLevelMenuID);
                if (user != null && oneLevelMenu != null) {
                    if (user.isTeacher()) {
                        oneLevelMenu.setTitle(title);
                        if (menuService.saveOneLevelMenu(oneLevelMenu) != null) {
                            return new ResponseEntity<>(oneLevelMenu, HttpStatus.OK);
                        } else {
                            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    } else {
                        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
                    }
                } else {
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
            } else {
                httpSession.invalidate();
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createTwoLevelMenu")
    public ResponseEntity<TwoLevelMenu> createTwoLevelMenu(
            @RequestParam(name = "userID") Long userID,
            @RequestParam(name = "parentID") Long parentID,
//            @RequestParam(name = "index") String index,
            @RequestParam(name = "title") String title,
            HttpServletRequest request
    ){
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                User user = userService.findUser(userID);
                OneLevelMenu oneLevelMenu = menuService.findOneLevelMenu(parentID);
//                TwoLevelMenu twoLevelMenu = menuService.findTwoLevelMenu(index);
                if (user != null) {
                    if (user.isTeacher()) {
                        if (oneLevelMenu != null) {
//                            if (twoLevelMenu == null) {
                                TwoLevelMenu _twoLevelMenu = menuService.saveTwoLevelMenu(new TwoLevelMenu(parentID, title));
                                return new ResponseEntity<>(_twoLevelMenu, HttpStatus.OK);
//                            } else {
//                                return new ResponseEntity<>(null, HttpStatus.ALREADY_REPORTED);
//                            }
                        } else {
                            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

                        }
                    } else {
                        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
                    }
                } else {
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
            } else {
                httpSession.invalidate();
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/deleteTwoLevelMenu")
    public ResponseEntity<TwoLevelMenu> deleteTwoLevelMenu(
            @RequestParam(name = "userID") Long userID,
            @RequestParam(name = "twoLevelMenuID") Long twoLevelMenuID,
            HttpServletRequest request
    ) {
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                User user = userService.findUser(userID);
                TwoLevelMenu twoLevelMenu = menuService.findTwoLevelMenu(twoLevelMenuID);
                if (user != null && twoLevelMenu != null) {
                    if (user.isTeacher()) {
                        if (menuService.deleteTwoLevelMenu(twoLevelMenu)) {
                            return new ResponseEntity<>(twoLevelMenu, HttpStatus.OK);
                        } else {
                            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    } else {
                        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
                    }
                } else {
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
            } else {
                httpSession.invalidate();
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/editTwoLevelMenu")
    public ResponseEntity<TwoLevelMenu> editTwoLevelMenu(
            @RequestParam(name = "userID") Long userID,
            @RequestParam(name = "twoLevelMenuID")Long twoLevelMenuID,
            @RequestParam(name = "title")String title,
            HttpServletRequest request
    ) {
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                User user = userService.findUser(userID);
                TwoLevelMenu twoLevelMenu = menuService.findTwoLevelMenu(twoLevelMenuID);
                if (user != null && twoLevelMenu != null) {
                    if (user.isTeacher()) {
                        twoLevelMenu.setTitle(title);
                        if (menuService.saveTwoLevelMenu(twoLevelMenu) != null) {
                            return new ResponseEntity<>(twoLevelMenu, HttpStatus.OK);
                        } else {
                            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    } else {
                        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
                    }
                } else {
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
            } else {
                httpSession.invalidate();
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/createThreeLevelMenu")
    public ResponseEntity<ThreeLevelMenu> createThreeMenu(
            @RequestParam(name = "userID") Long userID,
            @RequestParam(name = "parentID") Long parentID,
//            @RequestParam(name = "index") String index,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "fileType") String fileType,
            @RequestParam(name = "file")MultipartFile multipartFile,
            HttpServletRequest request
            ) {
//        return createMenuService.createThreeLevelMenuService(userID, parentID, index, title, fileType, multipartFile);
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                User user = userService.findUser(userID);
                TwoLevelMenu twoLevelMenu = menuService.findTwoLevelMenu(parentID);
//                ThreeLevelMenu threeLevelMenu = menuService.findThreeLevelMenu(index);
                if (user != null) {
                    if (user.isTeacher()) {
                        if (twoLevelMenu != null) {
//                            if (threeLevelMenu == null) {
                                if (multipartFile.getOriginalFilename() == null) {
                                    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
                                }
                                String rawFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

                                // get file suffix for separate different file url
                                String fileSuffix = rawFileName.substring(rawFileName.lastIndexOf("."));
                                String fileName = title.replace(" ", "") + fileSuffix;
                                String uploadDir = "/home/ubuntu/";
//                            String uploadDir = "E:/tempFile/";
                                String fileURL;
                                switch (fileType) {
                                    case "video":
                                        uploadDir += "video";
                                        fileURL = "/video/";
                                        break;
                                    case "slide":
                                        uploadDir += "slide";
                                        fileURL = "/slide/";
                                        break;
                                    case "compressed":
                                        uploadDir += "compressed";
                                        fileURL = "/compressed/";
                                        break;
                                    case "pdf":
                                        uploadDir += "pdf";
                                        fileURL = "/pdf/";
                                        break;
                                    default:
                                        uploadDir += "file";
                                        fileURL = "/file/";
                                        break;
                                }

                                ThreeLevelMenu _threeLevelMenu;
                                _threeLevelMenu = menuService.saveThreeLevelMenu(new ThreeLevelMenu(
                                        parentID,
//                                        index,
                                        title,
                                        fileType,
                                        fileURL + fileName
                                ));

                                if (_threeLevelMenu != null && fileService.saveFile(uploadDir, fileName, multipartFile)) {
                                    return new ResponseEntity<>(_threeLevelMenu, HttpStatus.OK);
                                } else {
                                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                                }
//                            } else {
//                                return new ResponseEntity<>(null, HttpStatus.ALREADY_REPORTED);
//                            }
                        } else {
                            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                        }
                    } else {
                        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
                    }
                } else {
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
            } else {
                httpSession.invalidate();
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/deleteThreeLevelMenu")
    public ResponseEntity<ThreeLevelMenu> deleteThreeLevelMenu(
            @RequestParam(name = "userID") Long userID,
            @RequestParam(name = "threeLevelMenuID") Long threeLevelMenuID,
            HttpServletRequest request
    ) {
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                User user = userService.findUser(userID);
                ThreeLevelMenu threeLevelMenu = menuService.findThreeLevelMenu(threeLevelMenuID);
                if (user != null && threeLevelMenu != null) {
                    if (user.isTeacher()) {
                        if (menuService.deleteThreeLevelMenu(threeLevelMenu)) {
                            return new ResponseEntity<>(threeLevelMenu, HttpStatus.OK);
                        } else {
                            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    } else {
                        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
                    }
                } else {
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
            } else {
                httpSession.invalidate();
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/updateThreeLevelMenuFile")
    public ResponseEntity<ThreeLevelMenu> editThreeLevelMenu(
            @RequestParam(name = "userID") Long userID,
            @RequestParam(name = "threeLevelMenuID")Long threeLevelMenuID,
            @RequestParam(name = "title")String title,
            @RequestParam(name = "fileType")String fileType,
            @RequestParam(name = "file")MultipartFile multipartFile,
            HttpServletRequest request
    ) {
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                User user = userService.findUser(userID);
                ThreeLevelMenu threeLevelMenu = menuService.findThreeLevelMenu(threeLevelMenuID);
                if (user != null && threeLevelMenu != null) {
                    if (user.isTeacher()) {
                        if (multipartFile.getOriginalFilename() == null) {
                            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
                        }
                        String rawFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

                        // get file suffix for separate different file url
                        String fileSuffix = rawFileName.substring(rawFileName.lastIndexOf("."));
                        String fileName = title.replace(" ", "") + fileSuffix;
                        String uploadDir = "/home/ubuntu/";
//                            String uploadDir = "E:/tempFile/";
                        String fileURL;
                        switch (fileType) {
                            case "video":
                                uploadDir += "video";
                                fileURL = "/video/";
                                break;
                            case "slide":
                                uploadDir += "slide";
                                fileURL = "/slide/";
                                break;
                            case "compressed":
                                uploadDir += "compressed";
                                fileURL = "/compressed/";
                                break;
                            case "pdf":
                                uploadDir += "pdf";
                                fileURL = "/pdf/";
                                break;
                            default:
                                uploadDir += "file";
                                fileURL = "/file/";
                                break;
                        }
                        threeLevelMenu.setTitle(title);
                        threeLevelMenu.setFileType(fileType);
                        threeLevelMenu.setFileURL(fileURL);
                        if (menuService.saveThreeLevelMenu(threeLevelMenu) != null &&
                                fileService.saveFile(uploadDir, fileName, multipartFile)) {
                            return new ResponseEntity<>(threeLevelMenu, HttpStatus.OK);
                        } else {
                            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    } else {
                        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
                    }
                } else {
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
            } else {
                httpSession.invalidate();
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/editThreeLevelMenuTitle")
    public ResponseEntity<ThreeLevelMenu> editThreeLevelMenu(
            @RequestParam(name = "userID") Long userID,
            @RequestParam(name = "threeLevelMenuID")Long threeLevelMenuID,
            @RequestParam(name = "title")String title,
            HttpServletRequest request
    ) {
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                User user = userService.findUser(userID);
                ThreeLevelMenu threeLevelMenu = menuService.findThreeLevelMenu(threeLevelMenuID);
                if (user != null && threeLevelMenu != null) {
                    if (user.isTeacher()) {
                        threeLevelMenu.setTitle(title);
                        if (menuService.saveThreeLevelMenu(threeLevelMenu) != null) {
                            return new ResponseEntity<>(threeLevelMenu, HttpStatus.OK);
                        } else {
                            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    } else {
                        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
                    }
                } else {
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
            } else {
                httpSession.invalidate();
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllMenu")
    public ResponseEntity<List<OneLevelMenu>> getAllMenu(
            HttpServletRequest request
    ){
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                List<OneLevelMenu> oneLevelMenuList = menuService.findAllOneLevelMenu();
                List<TwoLevelMenu> twoLevelMenuList = menuService.findAllTwoLevelMenu();
                for (OneLevelMenu oneLevelMenu :
                        oneLevelMenuList) {
                    Long oneLevelID = oneLevelMenu.getOneLevelMenuID();
                    List<TwoLevelMenu> oneLevelMenuChildren = menuService.findAllTwoLevelMenuByParentID(oneLevelID);
                    oneLevelMenu.setChildren(oneLevelMenuChildren);
                    for (TwoLevelMenu twoLevelMenu :
                            twoLevelMenuList) {
                        Long twoLevelID = twoLevelMenu.getTwoLevelMenuID();
                        List<ThreeLevelMenu> twoLevelMenuChildren = menuService.findAllThreeLevelMenuByParentID(twoLevelID);
                        twoLevelMenu.setChildren(twoLevelMenuChildren);
                    }
                }
                return new ResponseEntity<>(oneLevelMenuList, HttpStatus.OK);
            } else {
                httpSession.invalidate();
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getThreeLevelMenuByID")
    public ResponseEntity<ThreeLevelMenu> getThreeLevelMenuByIndex(
            @RequestParam(name = "threeLevelMenuID") Long threeLevelMenuID,
            HttpServletRequest request
    ){
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                ThreeLevelMenu threeLevelMenu = menuService.findThreeLevelMenu(threeLevelMenuID);
                if (threeLevelMenu != null)
                    return new ResponseEntity<>(threeLevelMenu, HttpStatus.OK);
                else
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                httpSession.invalidate();
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
