# Spring-REST-CRUD-Application
This application is based on Java and Spring framework. This application has 3-layer architecture implemented i.e Presentation, Service and Persistence Layer.<br />
It has APIs exposed which are listed below:

<p>
<u>Login API</u>
  <ul>
    <li> 
      <p>
        <strong>POST: </strong>http://localhost/Spring-REST-CRUD-Application/LoginAPI/auth<br />
        <h4>Request Body:</h4>
        <code>
          {
          "username": "roo",
          "password": "root"
          }
        </code>
      </p>
    </li>
   </ul>
</p>
<p>
<u>Author API</u>
  <ul>
    <li> 
      <p>
        <strong>GET: </strong>http://localhost/Spring-REST-CRUD-Application/AuthorAPI/getAuthor?email=marjin1@gmail.com<br />
      </p>
    </li>
    <li> 
      <p>
        <strong>POST: </strong>http://localhost/Spring-REST-CRUD-Application/AuthorAPI/addAuthor<br />
        <h4>Request Body:</h4>
        <code>
          {
    "authorName": "Marijn Haverbeke",
    "emailId": "marjin@gmail.com",
    "qualification": "MBA",
    "bookList": [
        {
            "bookName": "A Modern Introduction to Programming",
            "language": "English",
            "publishedDate": [
                2018,
                11,
                2
            ],
            "cost": 472.5
        }
    ],
    "successMessage": null,
    "errorMessage": null
    }
        </code>
      </p>
    </li>
   </ul>
</p>
