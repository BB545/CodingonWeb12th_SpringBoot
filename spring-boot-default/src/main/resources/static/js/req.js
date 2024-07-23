const dtoResultBox = document.getElementById('dto-result-container');
const voResultBox = document.getElementById('vo-result-container');

// 폼 데이터를 쿼리 문자열로 만들어 GET /axios/res1 요청
function dtoRes1() {
  const form = document.getElementById('form_dto1');
  axios
    .get(`/axios/res1?name=${form.name.value}&age=${form.age.value}`)
    .then((res) => {
      console.log('dtoRes1: ', res.data);
      dtoResultBox.textContent = res.data;
    });
}

// 폼 데이터를 쿼리 문자열로 만들어 GET /axios/res2 요청
function dtoRes2() {
  const form = document.getElementById('form_dto1');
  axios
    .get(`/axios/res2?name=${form.name.value}&age=${form.age.value}`)
    .then((res) => {
      console.log('dtoRes2: ', res.data);
      dtoResultBox.textContent = res.data;
    });
}

function dtoRes3() {
  const form = document.getElementById('form_dto2');
  axios
    .post(`/axios/res3`, { name: form.name.value, age: form.age.value })
    .then((res) => {
      console.log('dtoRes3: ', res.data);
    })
    .catch((error) => {
      console.error('Error in dtoRes3:', error);
      dtoResultBox.textContent = error.message;
    });
}

function dtoRes4() {
  const form = document.getElementById('form_dto2');
  axios
    .post(`/axios/res4`, { name: form.name.value, age: form.age.value })
    .then((res) => {
      console.log('dtoRes1: ', res.data);
      dtoResultBox.textContent = res.data;
    });
}

function dtoRes5() {
  const form = document.getElementById('form_dto2');
  axios
    .post(`/axios/res5`, { name: form.name.value, age: form.age.value })
    .then((res) => {
      console.log('dtoRes5: ', res.data);
      dtoResultBox.textContent = res.data;
    });
}

function voRes1() {
  const form = document.getElementById('form_vo1');
  axios
    .get(`/axios/vo/res1?name=${form.name.value}&age=${form.age.value}`)
    .then((res) => {
      console.log('voRes1: ', res.data);
      voResultBox.textContent = res.data;
    });
}

function voRes2() {
  const form = document.getElementById('form_vo1');
  axios
    .get(`/axios/vo/res2?name=${form.name.value}&age=${form.age.value}`)
    .then((res) => {
      console.log('voRes2: ', res.data);
      voResultBox.textContent = res.data;
    });
}

function voRes3() {
  const form = document.getElementById('form_vo2');
  axios
    .post(`/axios/vo/res3`, { name: form.name.value, age: form.age.value })
    .then((res) => {
      console.log('voRes3: ', res.data);
    })
    .catch((error) => {
      console.error('Error in voRes3:', error);
      voResultBox.textContent = error.message;
    });
}

function voRes4() {
  const form = document.getElementById('form_vo2');
  axios
    .post(`/axios/vo/res4`, { name: form.name.value, age: form.age.value })
    .then((res) => {
      console.log('voRes4: ', res.data);
      voResultBox.textContent = res.data;
    });
}

function voRes5() {
  const form = document.getElementById('form_vo2');
  axios
    .post(`/axios/vo/res5`, { name: form.name.value, age: form.age.value })
    .then((res) => {
      console.log('voRes5 : ', res.data);
      voResultBox.textContent = res.data;
    });
}

function voPrac1() {
  const form = document.getElementById('axiosVoPrac1');
  const hobbies = Array.from(form.querySelectorAll('input[name="hobby"]:checked')).map(hobby => hobby.value);
  axios
    .post(`/axios/signup`,
    { name: form.name.value,
      gender: form.gender.value,
      year: form.year.value,
      month: form.month.value,
      day: form.day.value,
      hobby: hobbies })
    .then((res) => {
      console.log(res.data + '님 회원가입 성공 ');
      voResultBox.textContent = res.data;
    });
}

function crudPrac1() {
  const form = document.getElementById('crudPrac1');
  axios
    .post(`/crud/signup`,
    { name: form.name.value,
      id: form.id.value,
      password: form.password.value,
      gender: form.gender.value,
      year: form.year.value,
      month: form.month.value,
      day: form.day.value })
    .then((res) => {
      console.log(res.data + '님 회원가입 성공 ');
    });
}

function crudPrac2() {
  const form = document.getElementById('crudPrac1');
  axios
    .post(`/crud/login`,
    { name: form.name.value,
      id: form.id.value,
      password: form.password.value,
      gender: form.gender.value,
      year: form.year.value,
      month: form.month.value,
      day: form.day.value })
    .then((res) => {
      console.log(res.data + '님 로그인 성공 ');
    });
}

function crudPrac3() {
  const form = document.getElementById('crudPrac1');
  axios
    .post(`/crud/modify`,
    { name: form.name.value,
      id: form.id.value,
      password: form.password.value,
      gender: form.gender.value,
      year: form.year.value,
      month: form.month.value,
      day: form.day.value })
    .then((res) => {
      console.log(res.data + '님 회원 정보 수정 성공 ');
    });
}

function crudPrac4() {
  const form = document.getElementById('crudPrac1');
  axios
    .post(`/crud/drop`,
    { name: form.name.value,
      id: form.id.value,
      password: form.password.value,
      gender: form.gender.value,
      year: form.year.value,
      month: form.month.value,
      day: form.day.value })
    .then((res) => {
      console.log(res.data + '님 회원 탈퇴 성공 ');
    });
}