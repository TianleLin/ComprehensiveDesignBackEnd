package com.example.springbootproject.controller;

import com.example.springbootproject.dao.LikeCommentReplyRepository;
import com.example.springbootproject.model.*;
import com.example.springbootproject.service.CommentReplyService;
import com.example.springbootproject.service.LikeService;
import com.example.springbootproject.service.MenuService;
import com.example.springbootproject.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@Api
//@CrossOrigin
@RequestMapping("/api")
public class CommentReplyController {


    @Autowired
    LikeService likeService;
    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;
    @Autowired
    CommentReplyService commentReplyService;
    @Autowired
    LikeCommentReplyRepository likeCommentReplyRepository;

    @PostMapping("/sendComment")
    public ResponseEntity<Comment> sendComment(
            @RequestParam(name = "threeLevelMenuID") Long threeLevelMenuID,
            @RequestParam(name = "userID") Long userID,
            @RequestParam(name = "content") String content,
            HttpServletRequest request
    ){
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                ThreeLevelMenu threeLevelMenu = menuService.findThreeLevelMenu(threeLevelMenuID);
                User user = userService.findUser(userID);
                if (threeLevelMenu != null && user != null) {
                    Comment comment = new Comment(threeLevelMenuID,
                            userID,
                            user.getUsername(),
                            user.getAvatar(),
                            content,
                            0L
                    );
                    comment = commentReplyService.saveComment(comment);
                    return new ResponseEntity<>(comment, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
            } else {
                httpSession.invalidate();
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/sendReply")
    public ResponseEntity<Reply> sendReply(
            @RequestParam(name = "threeLevelMenuID")Long threeLevelMenuID,
            @RequestParam(name = "commentOrReplyID")Long commentOrReplyID,
            @RequestParam(name = "userID")Long userID,
            @RequestParam(name = "content")String content,
            HttpServletRequest request
    ) {
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                CommentReply commentOrReply = commentReplyService.findCommentOrReply(commentOrReplyID);
                User user = userService.findUser(userID);
                if (commentOrReply != null && user != null) {
                    Reply _reply = new Reply(
                            threeLevelMenuID,
                            commentOrReplyID,
                            userID,
                            user.getUsername(),
                            user.getAvatar(),
                            content,
                            0L
                    );
                    _reply = commentReplyService.saveReply(_reply);
                    return new ResponseEntity<>(_reply, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
            } else {
                httpSession.invalidate();
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/editCommentOrReply")
//    public ResponseEntity<CommentReply> editCommentOrReply(
//            @RequestParam(name = "commentOrReplyID") Long commentOrReplyID,
//            @RequestParam(name = "userID") Long userID,
//            @RequestParam(name = "content") String content,
//            HttpServletRequest request
//    ) {
//        try {
//            HttpSession httpSession = request.getSession();
//            if(!httpSession.isNew()) {
//                CommentReply commentOrReply = commentReplyService.findCommentOrReply(commentOrReplyID);
//                User user = userService.findUser(userID);
//                if (commentOrReply != null && user != null) {
//                    if (commentOrReply instanceof Comment) {
//                        Comment _comment = (Comment) commentOrReply;
//                        if (Objects.equals(_comment.getUserID(), userID)) {
//                            _comment.setContent(content);
//                            _comment = commentReplyService.saveComment(_comment);
//                            if (_comment != null) {
//                                return new ResponseEntity<>(_comment, HttpStatus.OK);
//                            } else {
//                                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//                            }
//                        } else {
//                            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//                        }
//                    } else {
//                        Reply _reply = (Reply) commentOrReply;
//                        if (Objects.equals(_reply.getUserID(), userID)) {
//                            _reply.setContent(content);
//                            _reply = commentReplyService.saveReply(_reply);
//                            if (_reply != null) {
//                                return new ResponseEntity<>(_reply, HttpStatus.OK);
//                            } else {
//                                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//                            }
//                        } else {
//                            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//                        }
//                    }
//                } else {
//                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//                }
//            } else {
//                httpSession.invalidate();
//                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @PostMapping("/deleteCommentOrReply")
//    public ResponseEntity<CommentReply> deleteCommentOrReply(
//            @RequestParam(name = "commentOrReplyID") Long commentOrReplyID,
//            @RequestParam(name = "userID") Long userID,
//            HttpServletRequest request
//    ) {
//        try {
//            HttpSession httpSession = request.getSession();
//            if(!httpSession.isNew()) {
//                CommentReply commentOrReply = commentReplyService.findCommentOrReply(commentOrReplyID);
//                User user = userService.findUser(userID);
//                if (commentOrReply != null && user != null) {
//                    if (user.isTeacher() || Objects.equals(commentOrReply.getUserID(), userID)) {
//                        if (commentReplyService.deleteCommentOrReply(commentOrReply)) {
//                            return new ResponseEntity<>(commentOrReply, HttpStatus.OK);
//                        } else {
//                            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//                        }
//                    } else {
//                        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//                    }
//                } else {
//                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//                }
//            } else {
//                httpSession.invalidate();
//                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
//            }
//        } catch (Exception e){
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping("/likeCommentOrReply")
    public ResponseEntity<CommentReply> likeCommentOrReply(
            @RequestParam(name = "threeLevelMenuID")Long threeLevelMenuID,
            @RequestParam(name = "commentOrReplyID")Long commentOrReplyID,
            @RequestParam(name = "userID")Long userID,
            HttpServletRequest request
    ) {
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                ThreeLevelMenu threeLevelMenu = menuService.findThreeLevelMenu(threeLevelMenuID);
                CommentReply commentOrReply = commentReplyService.findCommentOrReply(commentOrReplyID);
                User user = userService.findUser(userID);
                if (commentOrReply != null && user != null && threeLevelMenu != null) {
//                    Optional<LikeCommentReply> like =
//                            Optional.ofNullable(
//                                    likeCommentReplyRepository.findByCommentReplyIDAndUserID(commentOrReplyID, userID));
//                    if(like.isPresent()){
//                        return new ResponseEntity<>(null, HttpStatus.ALREADY_REPORTED);
//                    } else {
//                        likeCommentReplyRepository.save(new LikeCommentReply(
//                                commentOrReplyID,
//                                userID
//                        ));
//                        return new ResponseEntity<>(like.get(), HttpStatus.OK);
//                    }
                    LikeCommentReply like = likeService.findLikeByCommentOrReplyIDAndUserID(commentOrReplyID, userID);
                    if (like == null) {
                        if (commentOrReply instanceof Comment) {
                            Comment _comment = (Comment) commentOrReply;
                            _comment.setCommentLike(_comment.getCommentLike() + 1);
                            _comment = commentReplyService.saveComment(_comment);
                            LikeCommentReply _likeCommentReply = likeService.saveLike(
                                    new LikeCommentReply(threeLevelMenuID, commentOrReplyID, userID)
                            );
                            if (_comment != null && _likeCommentReply != null) {
                                return new ResponseEntity<>(_comment, HttpStatus.OK);
                            } else {
                                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                            }
                        } else {
                            Reply _reply = (Reply) commentOrReply;
                            _reply.setReplyLike(_reply.getReplyLike() + 1);
                            _reply = commentReplyService.saveReply(_reply);
                            LikeCommentReply _likeCommentReply = likeService.saveLike(
                                    new LikeCommentReply(threeLevelMenuID, commentOrReplyID, userID)
                            );
                            if (_reply != null && _likeCommentReply != null) {
                                return new ResponseEntity<>(_reply, HttpStatus.OK);
                            } else {
                                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                            }
                        }
                    } else {
                        return new ResponseEntity<>(null, HttpStatus.ALREADY_REPORTED);
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

    @PostMapping("/dislikeCommentOrReply")
    public ResponseEntity<CommentReply> dislikeCommentOrReply(
            @RequestParam(name = "commentOrReplyID")Long commentOrReplyID,
            @RequestParam(name = "userID")Long userID,
            HttpServletRequest request
    ) {
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                CommentReply commentOrReply = commentReplyService.findCommentOrReply(commentOrReplyID);
                User user = userService.findUser(userID);
                if (commentOrReply != null && user != null) {
                    LikeCommentReply like = likeService.findLikeByCommentOrReplyIDAndUserID(commentOrReplyID, userID);
                    if (like != null) {
                        if (commentOrReply instanceof Comment) {
                            Comment _comment = (Comment) commentOrReply;
                            if (_comment.getCommentLike() > 0) {
                                _comment.setCommentLike(_comment.getCommentLike() - 1);
                            } else {
                                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                            }
                            _comment = commentReplyService.saveComment(_comment);
                            if (_comment != null && likeService.deleteLike(like)) {
                                return new ResponseEntity<>(_comment, HttpStatus.OK);
                            } else {
                                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                            }
                        } else {
                            Reply _reply = (Reply) commentOrReply;
                            if (_reply.getReplyLike() > 0) {
                                _reply.setReplyLike(_reply.getReplyLike() - 1);
                            } else {
                                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                            }
                            _reply = commentReplyService.saveReply(_reply);
                            if (_reply != null && likeService.deleteLike(like)) {
                                return new ResponseEntity<>(_reply, HttpStatus.OK);
                            } else {
                                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                            }
                        }
                    } else {
                        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
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

    @GetMapping("/getCommentAndReplyByThreeLevelMenuID")
    public ResponseEntity<List<Comment>> getCommentAndReplyByThreeLevelMenuID(
            @RequestParam(name = "threeLevelMenuID")Long threeLevelMenuID,
//            @RequestParam(name = "userID")Long userID,
            HttpServletRequest request
    ) {
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                ThreeLevelMenu threeLevelMenu = menuService.findThreeLevelMenu(threeLevelMenuID);
//                User user = userService.findUser(userID);
                if (threeLevelMenu != null) {
                    List<Comment> commentList = commentReplyService.findAllCommentByThreeLevelMenuID(threeLevelMenuID);
                    List<Reply> replyList = commentReplyService.findAllReplyByThreeLevelMenuID(threeLevelMenuID);

                    Map<Long, Reply> replyMap = new HashMap<>();
                    Map<Long, List<Reply>> parentMap = new HashMap<>();

                    for (Reply reply :
                            replyList) {
                        replyMap.put(reply.getReplyID(), reply);
                    }
                    for (Reply reply :
                            replyList) {
                        Reply parent = replyMap.get(reply.getParentID());
                        if (parent == null)
                            continue;
                        parent.getChildren().add(reply);
                    }
                    for (Reply reply :
                            replyList) {
                        Reply parent = replyMap.get(reply.getParentID());
                        if (parent == null) {
                            List<Reply> replyChildren = parentMap.getOrDefault(reply.getParentID(), new LinkedList<>());
                            replyChildren.add(reply);
                            parentMap.put(reply.getParentID(), replyChildren);
                        }
                    }
                    for (Comment comment :
                            commentList) {
                        List<Reply> commentChildren = parentMap.get(comment.getCommentID());
                        comment.setChildren(commentChildren);
                    }
                    return new ResponseEntity<>(commentList, HttpStatus.OK);
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
    @GetMapping("/getCommentOrReplyIDLikedByThreeLevelMenuIDAndUserID")
    public ResponseEntity<List<Long>> getCommentOrReplyIDLikedByThreeLevelMenuIDAndUserID(
            @RequestParam(name = "threeLevelMenuID")Long threeLevelMenuID,
            @RequestParam(name = "userID")Long userID,
            HttpServletRequest request
    ) {
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                ThreeLevelMenu threeLevelMenu = menuService.findThreeLevelMenu(threeLevelMenuID);
                User user = userService.findUser(userID);
                if (threeLevelMenu != null && user != null) {
                    List<Long> commentOrReplyIDList =
                            likeService.findCommentOrReplyIDByThreeLevelMenuIDAndUserID(threeLevelMenuID, userID);
                    return new ResponseEntity<>(commentOrReplyIDList, HttpStatus.OK);
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
}
