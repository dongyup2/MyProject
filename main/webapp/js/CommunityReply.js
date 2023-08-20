/**
 * 
 */const urlParams = new URLSearchParams(window.location.search);
    const postId = urlParams.get('postId');

    $(document).ready(function() {
        loadPostInfo(postId);
        
        $(".comment-toggle").on("click", function() {
            $(".comment-list-container").toggle();
          });
        getReplyList();
    });

    function loadPostInfo(postId) {
      $.ajax({
        url: "CommunityPostDetailServlet",
        type: "GET",
        data: { postId: postId },
        dataType: "json",
        success: function(data) {
		  console.log(data)
		  $("#postWriter").text(data.writer + " | ");
          $("#postTitle").text(data.title);
          $("#postCreateDate").text(data.create_Date);
          $("#postViews").text("조회수: " + data.views);
          $("#postRecommend").text("추천: " + data.recommend);
          $("#content").text(data.content);
          
        },
        error: function(xhr, status, error) {
          console.error("게시물 정보를 불러오지 못했습니다.");
        }
      });
    }
    
    
   
      function regReply() {
        const comment = $("#commentInput").val();
        const writer = $("#userId").val();

        $.ajax({
          url: "registReply",
          type: "POST",
          data: {
            bno: postId, // postId 가 이전 코드에서 사용하는 변수입니다.
            comment: comment,
            writer: writer
          },
          success: function (result) {
            if (parseInt(result) > 0) {
              alert("댓글이 등록되었습니다.");
              $("#tbody").empty();
              getReplyList();  // 댓글이 추가된 페이지를 다시 불러옵니다.
            } else {
              alert("댓글 등록에 실패했습니다. 다시 시도해주세요.");
            }
          },
          error: function (xhr, status, error) {
            console.error("댓글 등록에 실패했습니다.");
          },
        });
      }
      
      function getReplyList() {
          $.ajax({
              url: "replyList",
              type: "GET",
              data: {bno: postId},
              dataType: "json",
              success: function (replyList) {
                  let tbody = document.getElementById("tbody");
                  tbody.textContent = "";  // tbody 초기화

                  for (let i = 0; i < replyList.length; i++) {
                      const reply = replyList[i];
                      console.log(reply.comment);
                      const replyRow = `
          <tr>
            <td>${i + 1}</td>
            <td>${reply.comment}</td>
            <td>${reply.writer}</td>
            <td>${reply.regdate}</td>
            <td><a href="deleteReply?rno=${reply.rno}&bno=${reply.bno}">X</a> </td>
          </tr>`;
                      tbody.insertAdjacentHTML('beforeend', replyRow);
                  }
                  console.log("Current tbody.innerHTML:", tbody.innerHTML);
              },
              error: function (xhr, status, error) {
                  console.error("댓글 목록을 불러오는데 실패했습니다.");
              },
          });
      }
