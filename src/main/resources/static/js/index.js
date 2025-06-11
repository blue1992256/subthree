let user = window.userData;

document.addEventListener("DOMContentLoaded", () => {
  if (user != null && !user.isSignupComplete) {
    openModal();
  }
});

function socialLogin() {
  window.location.href = '/oauth2/authorization/kakao';
}

function logout() {
  window.location.href = '/logout';
}

// 데이터 제출 함수
async function submitData() {
  // 입력값 가져오기
  const username = document.getElementById("signup-username").value;
  const gender = document.querySelector('input[name="gender"]:checked');
  const height = document.getElementById("signup-height").value;
  const weight = document.getElementById("signup-weight").value;
  const runningYears = document.getElementById("signup-running-years").value;
  const data = {
    username: username,
    gender: gender.value,
    height: height,
    weight: weight,
    runningYears: runningYears,
    userId: user.userId
  }

  // 값이 입력되지 않았을 경우 경고
  if (!username || !gender || !height || !weight || !runningYears) {
    alert("모든 항목을 입력해주세요!");
    return;
  }

  try {
    const response = await fetch("/user/signup/info", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });

    const result = await response.json();
    console.log(result);
    if (result.status === 'success') {
      closeModal();
    } else if (result.status === 'dulicated') {
      alert('해당 아이디는 사용중입니다.');
    } else {
      alert('오류가 발생했습니다');
    }

  } catch (error) {
    console.error("에러 발생:", error);
  }
}

// 데이터 제출 함수
async function modifyData() {
  // 입력값 가져오기
  const gender = document.querySelector('input[name="gender"]:checked');
  const height = document.getElementById("height").value;
  const weight = document.getElementById("weight").value;
  const runningYears = document.getElementById("running-years").value;
  const data = {
    gender: gender.value,
    height: height,
    weight: weight,
    runningYears: runningYears,
    userId: user.userId
  }

  // 값이 입력되지 않았을 경우 경고
  if (!gender || !height || !weight || !runningYears) {
    alert("모든 항목을 입력해주세요!");
    return;
  }

  try {
    const response = await fetch("/user/modify/info", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });

    const result = await response.json();
    console.log(result);
    if (result.status === 'success') {
      closeModal();
    } else {
      alert('오류가 발생했습니다');
    }

  } catch (error) {
    console.error("에러 발생:", error);
  }
}

// 모달 열기
function openSignupModal() {
  document.getElementById("signupModal").style.display = "flex";
}

// 회원정보 수정 모달 열기
function openUserModal() {
  document.getElementById("myModal").style.display = "flex";
}

// 모달 닫기 함수
function closeModal() {
  document.getElementById("myModal").style.display = "none";
}

// 회원정보 수정 모달 닫기 함수
function closeSignupModal() {
  document.getElementById("signupModal").style.display = "none";
}

// 로그인 모달 열기
function openLoginModal() {
  document.getElementById("loginModal").style.display = "flex";
}

// 로그인 모달 닫기 함수
function closeLoginModal() {
  document.getElementById("loginModal").style.display = "none";
}

