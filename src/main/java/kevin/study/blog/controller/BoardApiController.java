package uniflow.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uniflow.blog.config.auth.PrincipalDetail;
import uniflow.blog.domain.Board;
import uniflow.blog.domain.Reply;
import uniflow.blog.dto.ResponseDto;
import uniflow.blog.service.BoardService;

@RestController
public class BoardApiController {

    private final BoardService boardService;

    @Autowired
    public BoardApiController(BoardService boardService) {
        this.boardService = boardService;
    }

    //글 쓰기
    @PostMapping("/board/write")
    public ResponseDto<Integer> save(@RequestBody Board board,
                                     @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.insertBoard(board, principal.getUser());
        return new ResponseDto<>(HttpStatus.OK.value(), 1); //JSON {"200": 1}
    }

    //글 수정
    @PutMapping("/board/update/{id}")
    public ResponseDto<Integer> update(@PathVariable String id,
                                     @RequestBody Board board) {
        boardService.updateBoard(Integer.parseInt(id), board);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    //글 삭제
    @DeleteMapping("/board/delete/{id}")
    public ResponseDto<Integer> delete(@PathVariable int id) {
        boardService.deleteBoard(id);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    //댓글 쓰기
    @PostMapping("/board/{boardId}/reply")
    public ResponseDto<Integer> saveReply(@PathVariable int boardId,
                                          @RequestBody Reply reply,
                                          @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.insertReply(boardId, reply, principal.getUser());
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    //댓글 삭제
    @DeleteMapping("/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> deleteReply(@PathVariable int boardId, @PathVariable int replyId) {
        boardService.deleteReply(replyId);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

}
