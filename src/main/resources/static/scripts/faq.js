$("body").delegate(".c-faq", "click", function () {
  $(".c-faq").removeClass("c-faq--active");
  $(this).addClass("c-faq--active");
});

$(document).ready(function () {
  $(".c-faq__title").on("click", function () {
    var $answerDiv = $(this).siblings(".show-answer");
    $answerDiv.toggle();
  });

  $(".answer-button").click(function () {
    var $form = $(this).next(".answer-form");
    $form.toggle();
  });
});
