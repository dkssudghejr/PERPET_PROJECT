var swiper = new Swiper(".slide1", {
    slidesPerView: 1,
    spaceBetween: 30,
    loop: true,
    pagination: {
      el: ".swiper-pagination",
      clickable: true,
    },
    navigation: {
      nextEl: ".swiper-button-next",
      prevEl: ".swiper-button-prev",
    },
    // 슬라이드 자동 넘김 4초
    autoplay: {
        delay: 4000,
    },
});

var swiper = new Swiper(".slide2", {
  slidesPerView: 1,
  spaceBetween: 30,
  loop: true,
  pagination: {
    el: ".swiper-pagination",
    clickable: true,
  },
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev",
  },
  // 슬라이드 자동 넘김 4초
  autoplay: {
    delay: 4000,
  },
});