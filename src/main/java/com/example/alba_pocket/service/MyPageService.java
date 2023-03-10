package com.example.alba_pocket.service;

import com.example.alba_pocket.dto.MsgResponseDto;
import com.example.alba_pocket.dto.MypageReqeustDto;
import com.example.alba_pocket.dto.MypageResponseDto;
import com.example.alba_pocket.dto.PostResponseDto;
import com.example.alba_pocket.entity.Post;
import com.example.alba_pocket.entity.User;
import com.example.alba_pocket.repository.LikesRepository;
import com.example.alba_pocket.repository.PostRepository;
import com.example.alba_pocket.repository.UserRepository;
import com.example.alba_pocket.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final UserRepository userRepository;
    private final LikesRepository likesRepository;
    private final PostRepository postRepository;
    private final S3Uploader s3Uploader;


    @Transactional
    public ResponseEntity<?> getMypage() {
        User user = SecurityUtil.getCurrentUser();
        MypageResponseDto mypageResponseDto = new MypageResponseDto(user);
        List<Post> posts = postRepository.findByUserOrderByCreatedAt(user);
        for (Post post : posts) {
            boolean isLike = false;
            if(user != null){
                isLike = likesRepository.existsByUserIdAndPostId(user.getId(), post.getId());
            }
            int likeCount = likesRepository.countByPostId(post.getId());
            mypageResponseDto.addPost(new PostResponseDto(post, isLike, likeCount));
        }

        return new ResponseEntity<>(mypageResponseDto, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> updateMypage(MultipartFile file, MypageReqeustDto data) throws IOException {
        User user = SecurityUtil.getCurrentUser();
        String imgUrl = null;
        if(!file.isEmpty()){
            imgUrl = s3Uploader.upload(file, "files");
        }
        user.updateUser(imgUrl, data.getNickname());
        userRepository.saveAndFlush(user);

        return new ResponseEntity<>(new MsgResponseDto("??????????????? ?????????????????????."), HttpStatus.OK);
    }
}
