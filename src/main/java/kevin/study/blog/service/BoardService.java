package uniflow.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uniflow.blog.domain.Board;
import uniflow.blog.domain.Reply;
import uniflow.blog.domain.User;
import uniflow.blog.repository.BoardRepository;
import uniflow.blog.repository.ReplyRepository;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, ReplyRepository replyRepository) {
        this.boardRepository = boardRepository;
        this.replyRepository = replyRepository;
    }

    //글 목록 보기
    @Transactional(readOnly = true)
    public Page<Board> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    //글 쓰기
    @Transactional
    public void insertBoard(Board board, User user) {
//        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    //글 상세 보기
    @Transactional(readOnly = true)
    public Board boardDetail(int id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
    }

    //글 삭제
    @Transactional
    public void deleteBoard(int id) {
        boardRepository.deleteById(id);
    }

    //글 수정
    @Transactional
    public void updateBoard(int id, Board board) {
        Board persistenceBoard = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
        persistenceBoard.setTitle(board.getTitle());
        persistenceBoard.setContent(board.getContent());
    }

    //댓글 쓰기
    @Transactional
    public void insertReply(int boardId, Reply reply, User user) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
        reply.setUser(user);
        reply.setBoard(board);
        replyRepository.save(reply);
    }

    //댓글 삭제
    @Transactional
    public void deleteReply(int replyId) {
        replyRepository.deleteById(replyId);
    }

}
