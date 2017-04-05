<%@ include file="include/header.jsp" %>
	
	<style>
		
		table {
            width: 100%;
        }

        thead, tbody, tr, td, th { display: block; }

        tr:after {
            content: ' ';
            display: block;
            visibility: hidden;
            clear: both;
        }

        thead th {
            height: 30px;

            /*text-align: left;*/
        }

        tbody {
            height: 400px;
            overflow-y: auto;
        }

        thead {
            /* fallback */
        }


        tbody td, thead th {
            width: 19.2%;
            float: left;
        }
		
		.bg-red {
		  background-color: #dd4b39 !important;
		}
		
		.bg-green {
		  background-color: #00a65a !important;
		}
		
		/*
			 * Component: Small Box
			 * --------------------
			 */
			.small-box {
			  border-radius: 2px;
			  position: relative;
			  display: block;
			  margin-bottom: 20px;
			  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
			}
			.small-box > .inner {
			  padding: 10px;
			}
			.small-box > .small-box-footer {
			  position: relative;
			  text-align: center;
			  padding: 3px 0;
			  color: #fff;
			  color: rgba(255, 255, 255, 0.8);
			  display: block;
			  z-index: 10;
			  background: rgba(0, 0, 0, 0.1);
			  text-decoration: none;
			}
			.small-box > .small-box-footer:hover {
			  color: #fff;
			  background: rgba(0, 0, 0, 0.15);
			}
			.small-box h3 {
			  font-size: 38px;
			  color: #fff;
			  font-weight: bold;
			  margin: 0 0 10px 0;
			  white-space: nowrap;
			  padding: 0;
			}
			.small-box p {
			  font-size: 15px;
			  color: #fff;
			}
			.small-box p > small {
			  display: block;
			  color: #f9f9f9;
			  font-size: 13px;
			  margin-top: 5px;
			}
			.small-box h3,
			.small-box p {
			  z-index: 5;
			}
			.small-box .icon {
			    -webkit-transition: all 0.3s linear;
			    -o-transition: all 0.3s linear;
			    transition: all 0.3s linear;
			    position: absolute;
			    top: -10px;
			    right: 10px;
			    z-index: 0;
			    font-size: 90px;
			    color: rgba(0, 0, 0, 0.15);
			}
			.small-box:hover {
			  text-decoration: none;
			  color: #f9f9f9;
			}
			.small-box:hover .icon {
			  font-size: 95px;
			}
			@media (max-width: 767px) {
			  .small-box {
			    text-align: center;
			  }
			  .small-box .icon {
			    display: none;
			  }
			  .small-box p {
			    font-size: 12px;
			  }
			}
		
		
	</style>
	
    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="container">
     <div class="row">
		    <div class="col-lg-3 col-xs-6">
		        <!-- small box -->
		        <div class="small-box ">
		            <div class="inner">
		                <h3>23</h3>
		                <p>Open Orders</p>
		            </div>
		           
		            
		        </div>
		    </div><!-- ./col -->
		    <div class="col-lg-3 col-xs-6">
		        <!-- small box -->
		        <div class="small-box ">
		            <div class="inner">
		                <h3>97<sup style="font-size: 20px">%</sup></h3>
		                <p>Closed Orders</p>
		            </div>
		            
		        </div>
		    </div><!-- ./col -->
		    <div class="col-lg-3 col-xs-6">
		        <!-- small box -->
		        <div class="small-box bg-green">
		            <div class="inner">
		                <h3 id="countok">0</h3>
		                <p>Corrette</p>
		            </div>
		            <div class="icon">
		                <i class="glyphicon glyphicon-ok"></i>
		            </div>
		            
		        </div>
		    </div><!-- ./col -->
		    <div class="col-lg-3 col-xs-6">
		        <!-- small box -->
		        <div class="small-box bg-red">
		            <div class="inner">
		                <h3 id="countko">0</h3>
		                <p>Errate</p>
		            </div>
		            <div class="icon">
		                <i class="glyphicon glyphicon-remove"></i>
		            </div>
		            
		        </div>
		    </div><!-- ./col -->
		</div><!-- /.row -->
    </div>
    
	<div class="container">
		<table class="table table-striped">
		  <thead>
		    <tr>
		      <th style="width:5%">#</th>
		      <th style="width:10%">A</th>
		      <th style="width:10%">B</th>
		      <th style="width:10%">C</th>
		      <th style="width:65%">Correct</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<c:forEach items="${testZoo.domande}" var="domanda">
		    <tr id="line${domanda.order}">
		      <td style="width:5%" scope="row"><b>${domanda.order}</b></td>
		      <td style="width:10%"><label class="radio-inline"><input type="radio" name="${domanda.order}" value="a">A</label></td>
		      <td style="width:10%"><label class="radio-inline"><input type="radio" name="${domanda.order}" value="b">B</label></td>
		      <td style="width:10%"><label class="radio-inline"><input type="radio" name="${domanda.order}" value="c">C</label></td>
		      <td style="width:65%"><input type="hidden" name="answer${domanda.order}" id="answer${domanda.order}" value="${domanda.giusta}">${domanda.giusta}</td>
		    </tr>
		    </c:forEach>
		  </tbody>
		</table>
	</div>
    
    <script>
    
     var correct = {};
     var incorrect = {};
     
     var totalCount = ${testZoo.numDom};
	
    $(document).ready(function () {
        $("input[type='radio']").on("change", function(){        
            //alert(this.name+" "+this.value);
            var $tr = $(this).closest('tr');
            var answer = this.value;
            var correctAnswer = $("#answer"+this.name).val();
            if (answer.toUpperCase() === correctAnswer.toUpperCase()){
            		correct[this.name] = this.name;
            		delete incorrect[this.name];
            		$tr.css('background-color', '#c1fd33');

            	} else{
            		incorrect[this.name] = this.name;
            		delete correct[this.name];
            	    //$tr.css('background-color', '#fc6a6c');#dd4b39
            	    $tr.css('background-color', '#dd4b39'); 
            	}
            
            var countCorrect = countProperties(correct);
            var countUncorrect = countProperties(incorrect);
            
            $("#countok").html(countCorrect);
            $("#countko").html(countUncorrect);
            
            if ((countCorrect+countUncorrect) == totalCount) {
            	$('#myModal').modal('show'); 
            }
            
        });
    });
    
    function countProperties (obj) {
        var count = 0;

        for (var property in obj) {
            if (Object.prototype.hasOwnProperty.call(obj, property)) {
                count++;
            }
        }

        return count;
    }
    </script>
    
<!-- Modal -->
<div class="modal fade" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title">Modal title</h4>
      </div>
      <div class="modal-body">
        <p>One fine body</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<%@ include file="include/footer.jsp" %>