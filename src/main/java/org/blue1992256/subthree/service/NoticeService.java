package org.blue1992256.subthree.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blue1992256.subthree.model.dto.NoticeDto;
import org.blue1992256.subthree.model.entity.Notice;
import org.blue1992256.subthree.model.vo.PageVo;
import org.blue1992256.subthree.repository.NoticeRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {

  private final NoticeRepository noticeRepository;

  public List<NoticeDto> getNoticeList(PageVo pageVo) {
    List<Notice> noticeList;
    pageVo.setTotalCount(noticeRepository.countAllBy());
    noticeList = noticeRepository.findAllBy(pageVo.getPageRequest());
    List<NoticeDto> noticeDtoList = new ArrayList<>();
    for (Notice notice : noticeList) {
      NoticeDto noticeDto = new NoticeDto();
      noticeDto.getShortDto(notice);
      noticeDtoList.add(noticeDto);
    }
    return noticeDtoList;
  }

  public boolean addNotice(NoticeDto NoticeDto) {
    Notice notice = new Notice();
    notice.setTitle(NoticeDto.getTitle());
    notice.setContent(NoticeDto.getContent());
    notice.setUsers(NoticeDto.getUser());
    notice.setStatus("published");
    noticeRepository.save(notice);
    return true;
  }

  public NoticeDto getNotice(Long id) {
    Notice notice = noticeRepository.findById(id).orElse(null);
    if (notice == null) {
      return new NoticeDto() ;
    }
    NoticeDto NoticeDto = new NoticeDto();
    NoticeDto.getFullDto(notice);
    return NoticeDto;
  }

  public void increaseViews(Long id) {
    noticeRepository.increaseViewCount(id);
  }

}
