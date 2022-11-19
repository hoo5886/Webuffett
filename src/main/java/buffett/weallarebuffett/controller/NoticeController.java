package buffett.weallarebuffett.controller;

import buffett.weallarebuffett.model.MemberEntity;
import buffett.weallarebuffett.model.Notice;
import buffett.weallarebuffett.model.NoticeEntity;
import buffett.weallarebuffett.repository.NoticeRepository;
import buffett.weallarebuffett.service.NoticeService;
import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;
    private final NoticeRepository noticeRepository;

    @GetMapping("/list")
    public String noticeList(Model model) {
        List<NoticeEntity> notices = noticeService.list();
        model.addAttribute("notices", notices);
        //model.addAttribute("notice", notice);

        return "notice/main";
    }

    @GetMapping("/write")
    public String write(Model model) {

        model.addAttribute("notice", new Notice());

        return "notice/write";
    }

    @PostMapping("/write")
    public String writeProc(@Valid Notice notice,
                            BindingResult result,
                            @AuthenticationPrincipal MemberEntity memberEntity) {
        if (result.hasErrors()) {
            return "notice/write";
        }

        noticeService.post(memberEntity.getId(), notice);

        return "redirect:/notice/list";
    }

    @GetMapping("/read/{id}")
    public String read(@PathVariable Long id, Model model) {

        NoticeEntity foundNotice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시물입니다."));

        noticeService.upHit(foundNotice);

        model.addAttribute("foundNotice", foundNotice);

        return "notice/read";
    }
}
