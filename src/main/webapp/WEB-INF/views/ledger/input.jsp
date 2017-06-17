


<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>   

<%@include file="../include/header.jsp"%>

<script type="text/javascript" src="/resources/js/upload.js"></script>


<!-- Main content -->
    <style type="text/css">
    .popup {position: absolute;}
    .back { background-color: gray; opacity:0.5; width: 100%; height: 300%; overflow:hidden;  z-index:1101;}
    .front { 
       z-index:1110; opacity:1; boarder:1px; margin: auto; 
      }
     .show{
       position:relative;
       max-width: 1200px; 
       max-height: 800px; 
       overflow: auto;       
     } 
     
    </style>

    <div class='popup back' style="display:none;"></div>
    <div id="popup_front" class='popup front' style="display:none;">
     <img id="popup_img">
    </div>

<section class="content">
   <div class="row">
      <!-- left column -->
      <div class="col-md-12">
         <!-- general form elements -->
         <div class="box box-primary">
            <div class="box-header">
               <h3 class="box-title">READ BOARD</h3>
            </div>
            <!-- /.box-header -->
            
            <form role="form" action="modifyPage" method="post">
            
               <input type='hidden' name='num' value="${ledBoardVO.num}">
               <input type='hidden' name='page' value="${cri.page}"> 
               <input type='hidden' name='perPageNum' value="${cri.perPageNum}">
               <input type='hidden' name='year' value="${cri.year}">
               <input type='hidden' name='month' value="${cri.month}">

            

            <div class="box-body">
               <div class="form-group">

                  <label for="exampleInputEmail1">Title</label>
                  <input type="text" name='title' class="form-control" value="${ledBoardVO.date}" readonly="readonly">
               </div>
               <div class="form-group">
                  <label for="exampleInputPassword1">Content</label>
                  <textarea class="form-control" id="contents" name="contents" rows="3">${ledBoardVO.contents}</textarea>
               </div>
               <%-- <div class="form-group">
                  <label for="exampleInputEmail1">Writer</label> <input type="text"
                     name="writer" class="form-control" value="${boardVO.writer}"
                     readonly="readonly">
               </div> --%>
            </div>
            <!-- /.box-body -->
            
            </form>
  <div class="box-footer">
    
    <div><hr></div>

    <ul class="mailbox-attachments clearfix uploadedList">
    </ul>
    <button type="submit" class="btn btn-warning" id="modifyBtn">메모수정</button>
    <!-- <button type="submit" class="btn btn-danger" id="removeBtn">REMOVE</button> -->
    <button type="submit" class="btn btn-primary" id="goListBtn">목록 </button>
    <form action="${path}/user/uploadForm" method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <input type="submit" value="업로드">    
    </form>
    </div>

         </div>
         <!-- /.box -->
      </div>
      <!--/.col (left) -->

   </div>
   <!-- /.row -->



   <div class="row">
      <div class="col-md-12">


<div class="box box-success">
  <div class="box-header">
    <h3 class="box-title">내역 추가</h3>
  </div>
  
  

  <div class="box-body">
  
  <form name=form1>
     <ul>
        
        <li>
           <input type="hidden" id="boardNum" value="${ledBoardVO.num}">
           <select name="BDIV" id="divide" class="sel_divide" onchange="fnCngList(this.value);">
              <option value="0" selected>지출</option>
              <option value="1">수입</option>
           </select>
           <select name="SDIV" id="category" class="sel_category">
              <option value="1" selected>식사</option>
              <option value="2">문화</option>
              <option value="3">교육</option>
              <option value="4">뷰티</option>
              <option value="5">학습</option>
              <option value="0">기타</option>
           </select>
        
        </li>
     </ul>
  </form>
  
    <input class="form-control" type="text" placeholder="상세 내역" id="context">     
    <input class="form-control" type="number" placeholder="금액" id="amount">
    </div>
  
      <div class="box-footer">
        <button type="submit" class="btn btn-primary" id="replyAddBtn">추가</button>
      </div>
  
    <!-- <div class="box-body">
      <div><a href="javascript:goLogin();" >Login Please</a></div>
    </div> -->
                          
</div>            


      
      <!-- The time line -->
      <ul class="timeline">
        <!-- timeline time label -->
        <li class="time-label" id="repliesDiv">
           <span class="bg-green">내역 보기
               <small id='replycntSmall'></small>
          </span>
        </li>
      </ul>
         
         <div class='text-center'>
            <ul id="pagination" class="pagination pagination-sm no-margin ">

            </ul>
         </div>

      </div>
      <!-- /.col -->
   </div>
   <!-- /.row -->


<!-- 내역 수정 -->
<!-- Modal -->
<div id="modifyModal" class="modal modal-primary fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"></h4>
      </div>
      <div class="modal-body" data-rno>
      
        <p><input type="text" id="replytext" class="form-control" value = "  "></p>
        <p><input type="text" id="replytext" class="form-control" value = "  "></p>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-info" id="replyModBtn">수정</button>
        <button type="button" class="btn btn-danger" id="replyDelBtn">삭제</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>      
   
   
</section>
<!-- /.content -->

<script>

alert("${ledgerVO}");
var myData ="${ledgerVO}"
var myJdata = JSON.stringify(myData);
alert(myJdata);
</script>

<script id="templateAttach" type="text/x-handlebars-template">
<li data-src='{{fullName}}'>
  <span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
  <div class="mailbox-attachment-info">
   <a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
   </span>
  </div>
</li>                
</script>  


          
<script id="template" type="text/x-handlebars-template">
   {{#each .}}
         <li class="replyLi" data-rno={{num}}>
             <i class="fa fa-comments bg-blue"></i>
             <div class="timeline-item" >
                <span class="time">
                  <i class="fa fa-clock-o"></i>{{prettifyDate regdate}}
                </span>
                <h3 class="timeline-header"><strong>{{num}}</strong> {{divide}}</h3>
                <div class="timeline-body">{{category}} </div>
            <div class="timeline-body2">{{context}} </div>
            <div class="timeline-footer">
                  {{amount}}
                      <a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modifyModal">수정</a>
            </div>
               </div>         
           </li>
   {{/each}}

alert("d");
</script>  

<script>
   //이중 셀렉트박스
   function fnCngList(sVal){
      var f = document.form1;
      var opt = $("#category").length;
      
      if(sVal == "0") {
         num = new Array("식사", "문화", "교육", "뷰티", "학습", "기타");
         vnum = new Array("1","2", "3", "4", "5", "0");
      }else if(sVal == "1") {
         num = new Array("주수입", "부수입", "대출/금융", "기타");
         vnum = new Array("1", "2", "3", "0");
      }
      
      for(var i=0; i<opt; i++) {
         f.SDIV.options[0] = null;
      }
      
      for(k=0;k < num.length; k++) {
         f.SDIV.options[k] = new Option(num[k], vnum[k]);
      }
   }

   
/*    Handlebars.registerHelper("eqReplyer", function(replyer, block) {
      var accum = '';
      if (replyer == '${login.uid}') {
         accum += block.fn();
      }
      return accum;
   }); */

   
   
   
   Handlebars.registerHelper("prettifyDate", function(timeValue) {
      var dateObj = new Date(timeValue);
      var year = dateObj.getFullYear();
      var month = dateObj.getMonth() + 1;
      var date = dateObj.getDate();
      return year + "/" + month + "/" + date;
   });

   var printData = function(replyArr, target, templateObject) {

      var template = Handlebars.compile(templateObject.html());

      var html = template(${ledgerVO});
      $(".replyLi").remove();
      target.after(html);
      alert("${ledgerVO}");
      //[{context:"hell yeah",regdate:"2016",num:30,amount:3000}]
   }
   
   
   

   var bno = ${ledBoardVO.num};
   //var bno = $(".abc").attr("value");
   
   var replyPage = 1;

   function getPage(pageInfo) {

      $.getJSON(pageInfo, function(data) {
         printData(data.list, $("#repliesDiv"), $('#template'));
         //printPaging(data.pageMaker, $(".pagination"));

         $("#modifyModal").modal('hide');
         //$("#replycntSmall").html("[ " + data.pageMaker.totalCount + " ]");

      });
   }

   /* var printPaging = function(pageMaker, target) {

      var str = "";

      if (pageMaker.prev) {
         str += "<li><a href='"+(pageMaker.startPage-1)+"'> << </a></li>";
      }

      for (var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
         var strClass = pageMaker.cri.page == i ? 'class=active' : '';
         str += "<li "+strClass+"><a href='"+i+"'>" + i + "</a></li>";
      }

      if (pageMaker.next) {
         str += "<li><a href='" + (pageMaker.endPage + 1)
               + "'> >> </a></li>";
      }

      target.html(str);
   }; */
   
   
   $("#repliesDiv").on("click", function() {

      if ($(".timeline li").size() > 1) {
         return;
      }
      getPage("/replies/" + bno); // + "/1");

   });

/*     $(".pagination").on("click", "li a", function(event) {

      event.preventDefault();

      replyPage = $(this).attr("href");

      getPage("/replies/" + bno);// + "/" + replyPage);

   });  */

   $("#replyAddBtn").on("click", function() {
      
      var boardNumObj = $("#boardNum")
      var divideObj = $("#divide");
      var categoryObj = $("#category");
      var contextObj = $("#context");
      var amountObj = $("#amount");
      
      var boardNum = boardNumObj.val();
      var divide = divideObj.val();
      var category = categoryObj.val();
      var context = contextObj.val();
      var amount = amountObj.val();
      
      if(divide ==0) {
         amount = -amount;
      }
      

      $.ajax({
         type : 'post',
         url : '/replies/',
         headers : {
            "Content-Type" : "application/json",
            "X-HTTP-Method-Override" : "POST"
         },
         dataType : 'text',
         data : JSON.stringify({
            boardNum : boardNum,
            divide : divide,
            category : category,
            context : context,
            amount : amount
         }),
         success : function(result) {
            console.log("result: " + result);
            if (result == 'SUCCESS') {
               alert("등록 되었습니다.");
               replyPage = 1;
               getPage("/replies/" + boardNum); // + "/" + replyPage);
               //contextObj.val("");
               amountObj.val("");
            }
         }
      });
   });

   $(".timeline").on("click", ".replyLi", function(event) {

      var reply = $(this);
      
      $("#divide").val(reply.find('.timeline-header').text());
      $("#category").val(reply.find('.timeline-body').text());
      $("#context").val(reply.find('.timeline-body2').text());
      $("#amount").val(reply.find('.timeline-footer').text());
      $(".modal-title").html(reply.attr("data-rno"));
      

   });

   $("#replyModBtn").on("click", function() {

      var rno = $(".modal-title").html();
      var replytext = $("#replytext").val();

      $.ajax({
         type : 'put',
         url : '/replies/' + rno,
         headers : {
            "Content-Type" : "application/json",
            "X-HTTP-Method-Override" : "PUT"
         },
         data : JSON.stringify({
            replytext : replytext
         }),
         dataType : 'text',
         success : function(result) {
            console.log("result: " + result);
            if (result == 'SUCCESS') {
               alert("수정 되었습니다.");
               getPage("/replies/" + bno); // + "/" + replyPage);
            }
         }
      });
   });

   $("#replyDelBtn").on("click", function() {

      var rno = $(".modal-title").html();
      var replytext = $("#replytext").val();

      $.ajax({
         type : 'delete',
         url : '/replies/' + rno,
         headers : {
            "Content-Type" : "application/json",
            "X-HTTP-Method-Override" : "DELETE"
         },
         dataType : 'text',
         success : function(result) {
            console.log("result: " + result);
            if (result == 'SUCCESS') {
               alert("삭제 되었습니다.");
               getPage("/replies/" + bno); // + "/" + replyPage);
            }
         }
      });
   });
</script>
<script>

    function addFilePath(msg){
        console.log(msg); // 파일명 콘솔 출력
        document.getElementById("form1").reset(); // ifream에 업로드결과를 출력 후 form에 저장된 데이터 초기화
    }
</script>


<script>
$(document).ready(function(){
   
   var formObj = $("form[role='form']");
   
   console.log(formObj);
   
   $("#modifyBtn").on("click", function(){
      formObj.attr("action", "/ledger/modifyPage");
      formObj.attr("method", "post");      
      formObj.submit();
   });
   
/*    $("#removeBtn").on("click", function(){
      formObj.attr("action", "/ledger/removePage");
      formObj.submit();
   }); */

   
   $("#removeBtn").on("click", function(){
      
      var replyCnt =  $("#replycntSmall").html();
      
      if(replyCnt > 0 ){
         alert("댓글이 달린 게시물을 삭제할 수 없습니다.");
         return;
      }   
      
      var arr = [];
      $(".uploadedList li").each(function(index){
          arr.push($(this).attr("data-src"));
      });
      
      console.log(arr);
       if(arr.length > 0){
         $.post("/deleteAllFiles",{files:arr}, function(){
            
            formObj.attr("action", "/ledger/removePage");
            formObj.submit();
            
         });
      }else{
         
         formObj.attr("action", "/ledger/removePage");
         formObj.submit();
      }
      
       /*
      formObj.attr("action", "/ledger/removePage");
      formObj.submit();
       */
   });   
   
   $("#goListBtn ").on("click", function(){
      formObj.attr("method", "get");
      formObj.attr("action", "/ledger/list");
      formObj.submit();
   });
   
   var bno = ${ledBoardVO.num};
   var template = Handlebars.compile($("#templateAttach").html());
   
   $.getJSON("/ledger/getAttach/"+bno,function(list){
      $(list).each(function(){
         
         var fileInfo = getFileInfo(this);
         
         var html = template(fileInfo);
         
          $(".uploadedList").append(html);
         
      });
   });
   


   $(".uploadedList").on("click", ".mailbox-attachment-info a", function(event){
      
      var fileLink = $(this).attr("href");
      
      if(checkImageType(fileLink)){
         
         event.preventDefault();
               
         var imgTag = $("#popup_img");
         imgTag.attr("src", fileLink);
         
         console.log(imgTag.attr("src"));
               
         $(".popup").show('slow');
         imgTag.addClass("show");      
      }   
   });
   
   $("#popup_img").on("click", function(){
      
      $(".popup").hide('slow');
      
   });   
   
      
   
});
/* 
 
function goLogin(){
   self.location ="/user/login";
}
  */

</script>






<%@include file="../include/footer.jsp"%>