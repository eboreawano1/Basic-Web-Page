    <header role="banner">
     
      <nav class="navbar navbar-expand-md navbar-dark bg-light">
        <div class="container">
          <a class="navbar-brand" href="index.jsp">Basic Web Page</a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample05" aria-controls="navbarsExample05" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>

          <div class="collapse navbar-collapse navbar-light" id="navbarsExample05">
            <ul class="navbar-nav ml-auto pl-lg-5 pl-0">
              <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">Home</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/Controller?page=list_users">List Users</a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" href="${pageContext.request.contextPath}/Controller?page=add_user">Add Users</a>
              </li>
            </ul>
            
          </div>
        </div>
      </nav>
    </header>
    <!-- END header -->