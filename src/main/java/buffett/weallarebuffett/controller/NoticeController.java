package buffett.weallarebuffett.controller;

import buffett.weallarebuffett.model.CommentEntity;
import buffett.weallarebuffett.model.MemberEntity;
import buffett.weallarebuffett.model.NoticeDto;
import buffett.weallarebuffett.model.NoticeEntity;
import buffett.weallarebuffett.repository.NoticeRepository;
import buffett.weallarebuffett.service.NoticeService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;
    private final NoticeRepository noticeRepository;

    @GetMapping("/list")
    public String noticeList(Model model) {
        List<NoticeEntity> noticeEntities = noticeService.list();
        model.addAttribute("notices", noticeEntities);
        //model.addAttribute("notice", notice);

        return "notice/main";
    }

    @GetMapping("/write")
    public String post(Model model) {

        model.addAttribute("notice", new NoticeDto());

        return "notice/write";
    }

    @PostMapping("/write")
    public String postProc(@Valid NoticeDto noticeDto,
                            BindingResult result,
                            @AuthenticationPrincipal MemberEntity member) {
        if (result.hasErrors()) {
            return "notice/write";
        }

        noticeService.post(member.getId(), noticeDto);

        return "redirect:/notice/list";
    }


    //게시물 페이지 안에 댓글을 주입하는 작업.
    @GetMapping("/read/{id}")
    public String read(@PathVariable Long id, Model model) {

        NoticeEntity notice = noticeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("존재하지 않습니다."));
        List<CommentEntity> comments = notice.getCommentEntities();

        //댓글
        if (comments != null && !comments.isEmpty()) {
            model.addAttribute("comments", comments);
        }

        NoticeEntity foundNoticeEntity = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시물입니다."));



        noticeService.upHit(foundNoticeEntity);
        model.addAttribute("foundNotice", foundNoticeEntity);
        return "notice/read";
    }
}
