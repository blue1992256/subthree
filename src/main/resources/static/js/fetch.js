function addBoard() {
  let form = new FormData();
  form.append("title", document.getElementById('title').value);
  form.append("content", document.getElementById('content').value);

  fetch('/board/submit', {
    method: 'post',
    body: form,
    headers: {}
  })
  .then(response => {
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    return response.text();
  })
  .then(data => {
    if (data === 'success') {
      location.href = '/board';
    } else {
      alert('에러가 발생했습니다.');
    }
  }) // 성공 시 콜백 실행
  .catch(err => {
    console.error(err);
  }); // 오류 발생 시 콜백 실행
}

function submitComment() {
  let form = new FormData();
  form.append("content", document.getElementById('commentInput').value);
  form.append("id", document.getElementById('board_id').value);

  fetch('/board/comment/submit', {
    method: 'post',
    body: form,
    headers: {}
  })
  .then(response => {
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    return response.text();
  })
  .then(data => {
    if (data === 'success') {
      location.href = '/board/' + document.getElementById('board_id').value;
    } else {
      alert('에러가 발생했습니다.');
    }
  }) // 성공 시 콜백 실행
  .catch(err => {
    console.error(err);
  }); // 오류 발생 시 콜백 실행
}

function recommendPost() {
  let form = new FormData();
  form.append("id", document.getElementById('board_id').value);

  fetch('/board/likes/submit', {
    method: 'post',
    body: form,
    headers: {}
  })
  .then(response => {
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    return response.text();
  })
  .then(data => {
    if (data === 'success') {
      location.href = '/board/' + document.getElementById('board_id').value;
    } else if (data === 'already liked') {
      alert('이미 추천을 했습니다.');
    } else {
      alert('에러가 발생했습니다.');
    }
  }) // 성공 시 콜백 실행
  .catch(err => {
    console.error(err);
  }); // 오류 발생 시 콜백 실행
}

function deleteComment(id) {
  let form = new FormData();
  form.append("id", id);

  fetch('/board/comment', {
    method: 'delete',
    body: form,
    headers: {}
  })
  .then(response => {
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    return response.text();
  })
  .then(data => {
    if (data === 'success') {
      location.href = '/board/' + document.getElementById('board_id').value;
    } else {
      alert('에러가 발생했습니다.');
    }
  }) // 성공 시 콜백 실행
  .catch(err => {
    console.error(err);
  }); // 오류 발생 시 콜백 실행
}

function addNotice() {
  let form = new FormData();
  form.append("title", document.getElementById('title').value);
  form.append("content", document.getElementById('content').value);

  fetch('/notice/submit', {
    method: 'post',
    body: form,
    headers: {}
  })
  .then(response => {
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    return response.text();
  })
  .then(data => {
    if (data === 'success') {
      location.href = '/notice';
    } else if (data === 'unauthorized') {
      alert('권한이 없습니다.');
    } else {
      alert('에러가 발생했습니다.');
    }
  }) // 성공 시 콜백 실행
  .catch(err => {
    console.error(err);
  }); // 오류 발생 시 콜백 실행
}
