# BIT_FinalProject(theHouse)
# springboot+thymeleaf+gradle

#니가사는그집 스프링부트 프로젝트

-'오늘의집'을 벤치마킹한 인테리어 쇼핑몰 및 커뮤니티(일반회원, 업체, 관리자 중점으로 기능 분담)

#담당파트

1.딥러닝을 이용한 상품추천(DL4J, word2vec)

-구현 코드(recommenderProcess)

2.stomp.js와 레디스를 이용한 채팅프로그램

-구현 코드(chattingProcess)

3.메인 상품 랜덤 무한스크롤, 스토어베스트페이지

-구현 코드(MainController)

#사용기술

-Front-End :　HTML5, CSS3, JavaScript, JQuery, AJAX, Bootstrap, Stomp.js

-Back-End : Java, Spring Boot 2.3.0, Websocket, Mybatis, gradle, Thymeleaf, MySQL, redis, DL4J(딥러닝)

-Tool : IntelliJ, GitHub, eXerd, starUML

※주의사항 - 추천트레이닝파일에 없는 상품 클릭후 메인페이지로 나올시 500에러 ex)신규상품, 상품모델명 수정, 상품삭제시
오류발생시 clickproduct테이블에서 해당 상품 삭제하면 해결됨
트레이닝을 원할경우 상품 등록후 clickproduct 테이블에 비슷한 상품이랑 함께 넣어 데이터 만든후 관리자-회원관리-추천트레이닝 접속후 트레이닝하기 클릭하면 해결됨.

#My part
-Item recommendation program using deeplearning4j and word2vec

-Chatting program using stomp.js and redis

-Main page(Infinite scroll), store best page

#Used skills and tools
-Front-End :　HTML5, CSS3, JavaScript, JQuery, AJAX, Bootstrap, Stomp.js

-Back-End : Java, Spring Boot 2.3.0, Websocket, Mybatis, gradle, Thymeleaf, MySQL, redis, DL4J(딥러닝)

-Tool : IntelliJ, GitHub, eXerd, starUML

※To be improved
-When I click one product which is not included in recommendation training file such as new products or deleted products, 
there is 500 error because training procedure is not connected automatically.
