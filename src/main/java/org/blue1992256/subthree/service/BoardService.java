package org.blue1992256.subthree.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blue1992256.subthree.model.dto.BoardsDto;
import org.blue1992256.subthree.model.entity.BoardLikes;
import org.blue1992256.subthree.model.entity.Boards;
import org.blue1992256.subthree.model.entity.Comments;
import org.blue1992256.subthree.model.vo.PageVo;
import org.blue1992256.subthree.repository.BoardLikesRepository;
import org.blue1992256.subthree.repository.BoardsRepository;
import org.blue1992256.subthree.repository.CommentsRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

  private final BoardsRepository boardsRepository;
  private final BoardLikesRepository boardLikesRepository;
  private final CommentsRepository commentsRepository;

  public List<BoardsDto> getBoardsList(PageVo pageVo) {
    List<Boards> boardList;
    switch (pageVo.getSearchType()) {
      case "title" -> {
        pageVo.setTotalCount(boardsRepository.countAllByTitle(pageVo.getKeyword()));
        boardList = boardsRepository.findAllByTitle(pageVo.getKeyword(), pageVo.getPageRequest());
      }
      case "content" -> {
        pageVo.setTotalCount(boardsRepository.countAllByContent(pageVo.getKeyword()));
        boardList = boardsRepository.findAllByContent(pageVo.getKeyword(), pageVo.getPageRequest());
      }
      case "titleAndContent" -> {
        pageVo.setTotalCount(boardsRepository.countAllByTitleAndContent(pageVo.getKeyword()));
        boardList = boardsRepository.findAllByTitleAndContent(pageVo.getKeyword(), pageVo.getPageRequest());
      }
      default -> {
        pageVo.setTotalCount(boardsRepository.countAllBy());
        boardList = boardsRepository.findAllBy(pageVo.getPageRequest());
      }
    }
    List<BoardsDto> boardDtoList = new ArrayList<>();
    for (Boards board : boardList) {
      board.setLikes(boardLikesRepository.countByBoardsId(board.getId()));
      BoardsDto boardDto = new BoardsDto();
      boardDto.getShortDto(board);
      boardDtoList.add(boardDto);
    }
    return boardDtoList;
  }

  public boolean addBoards(BoardsDto boardsDto) {
    Boards boards = new Boards();
    boards.setTitle(boardsDto.getTitle());
    boards.setContent(boardsDto.getContent());
    boards.setUsers(boardsDto.getUser());
    boards.setStatus("published");
    boardsRepository.save(boards);
    return true;
  }

  public BoardsDto getBoards(Long id) {
    Boards boards = boardsRepository.findById(id).orElse(null);
    if (boards == null) {
      return new BoardsDto() ;
    }
    boards.setLikes(boardLikesRepository.countByBoardsId(boards.getId()));
    BoardsDto boardsDto = new BoardsDto();
    boardsDto.getFullDto(boards);
    return boardsDto;
  }

  public void increaseViews(Long id) {
    boardsRepository.increaseViewCount(id);
  }

  public boolean increaseLikes(BoardsDto boardsDto) {
    if (boardLikesRepository.existsByBoardsIdAndUsers(boardsDto.getId(), boardsDto.getUser())) {
      return false;
    }
    BoardLikes boardLikes = new BoardLikes();
    boardLikes.setBoardsId(boardsDto.getId());
    boardLikes.setUsers(boardsDto.getUser());
    boardLikesRepository.save(boardLikes);
    return true;
  }

  public boolean submitComment(BoardsDto boardsDto) {
    Optional<Boards> optionalBoards = boardsRepository.findById(boardsDto.getId());
    if (!optionalBoards.isPresent()) {
      return false;
    }
    Comments comments = new Comments();
    comments.setBoards(optionalBoards.get());
    comments.setUsers(boardsDto.getUser());
    comments.setContent(boardsDto.getContent());
    comments.setStatus("published");
    commentsRepository.save(comments);
    return true;
  }

  public boolean deleteComment(BoardsDto boardsDto) {
    Optional<Comments> optionalComments = commentsRepository.findById(boardsDto.getId());
    if (!optionalComments.isPresent()) {
      return false;
    }
    Comments comments = optionalComments.get();
    if (!comments.getUsers().equals(boardsDto.getUser())) {
      return false;
    }
    comments.setStatus("deleted");
    comments.setModDate(LocalDateTime.now());
    commentsRepository.save(comments);
    return true;
  }

}
