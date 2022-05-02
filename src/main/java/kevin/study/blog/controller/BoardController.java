package uniflow.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uniflow.blog.service.BoardService;

@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    //메인 화면
    @GetMapping("/")
    public String index(Model model,
                        @PageableDefault(size=20, sort="id", direction= Sort.Direction.DESC)Pageable pageable) {
        model.addAttribute("boardList", boardService.boardList(pageable));
        return "index";
    }

    //글 쓰기 화면
    @GetMapping("/board/writeForm")
    public String writeForm() {
        return "board/writeForm";
    }

    //글 상세 보기 화면
    @GetMapping("/board/{id}")
    public String findBoard(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.boardDetail(id));
        return "board/detail";
    }

    //글 수정 화면
    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.boardDetail(id));
        return "board/updateForm";
    }

}
