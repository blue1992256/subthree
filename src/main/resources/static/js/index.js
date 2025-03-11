let user = window.userData;

document.addEventListener("DOMContentLoaded", () => {
  if (user != null && !user.isSignupComplete) {
    document.getElementById("myModal").style.display = "flex";
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
    } else {
      alert('오류가 발생했습니다');
    }

  } catch (error) {
    console.error("에러 발생:", error);
  }
}

// 모달 닫기 함수
function closeModal() {
  document.getElementById("myModal").style.display = "none";
}

