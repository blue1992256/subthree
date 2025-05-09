package org.blue1992256.subthree.model.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

@Getter
@Setter
public class PageVo {

  private String type1;
  private String type2;
  private String keyword;
  private String searchType = "";

  private int pageSize = 10; // 한 페이지당 10개씩
  private int page = 1;        // 현재 페이지 (1부터 시작)
  private int totalCount;  // 전체 리스트 출력 개수
  private int totalPages;  // 전체 페이지 수

  public PageRequest getPageRequest() {
    this.totalPages = Math.max(((totalCount + pageSize - 1) / pageSize), 1);
    return PageRequest.of(page - 1, pageSize);
  }

}
