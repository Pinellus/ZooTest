<%@ include file="include/header.jsp" %>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
      	<h2>Make New Test</h2>
        <form class="form" action="/generatePdf" method="post">
          <div class="form-group">
		    <label for="nome">Nome Test (senza spazi):</label>
		    <input type="text" class="form-control" id="nome" maxlength="10" name="nome" required="required">
		  </div>
		  <div class="form-group">
		    <label for="testnum">Numero di Test:</label>
		    <input type="number" class="form-control" id="testnum" min="1" max="99" name="testnum" required="required">
		  </div>
		  <div class="form-group">
		    <label for="dnum">Numero di domande:</label>
		    <input type="number" class="form-control" id="dnum" name="dnum" min="1" max="99" required="required">
		  </div>
		  <button type="submit" class="btn btn-default">Crea Test</button>
		</form>
      </div>
    </div>

    <div class="container">
      <!-- Example row of columns 
      <div class="row">
        <div class="col-md-4">
          <h2>Heading</h2>
          <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
          <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
        </div>
        <div class="col-md-4">
          <h2>Heading</h2>
          <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
          <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
       </div>
        <div class="col-md-4">
          <h2>Heading</h2>
          <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
          <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
        </div>
      </div>
	  -->
    </div> <!-- /container -->

<%@ include file="include/footer.jsp" %>
    