package buffett.weallarebuffett.controller;

import buffett.weallarebuffett.model.MemberEntity;
import buffett.weallarebuffett.model.Notice;
import buffett.weallarebuffett.model.NoticeEntity;
import buffett.weallarebuffett.service.NoticeService;
import java.time.LocalDateTime;
import java.util.List;
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

    /*@GetMapping("/post")
    public String post(Model model) {
        model.addAttribute("notice", new Notice());

        return "notice/postNotice";
    }

    @PostMapping("/post")
    public String postProc(@Valid Notice notice, BindingResult result) {
        if (result.hasErrors()) {
            return "/notice/Notice";
        }

        noticeService.post(notice);
        return "notice/postNotice";
    }*/

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
}
