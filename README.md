# Microservice_Product

<h1>[LOADING.... 56%]</h1>

<h2> Important </h2>

<p> In this new program I correct and improve my knowledge with spring boot and mongo db, before in the old project called "Workshop_MongoDB" I had created an auxiliary class to transform timestamp id's to Long format id's, starting this new project I was able to review and learn that we should treat the id's in the mongo db as Strings and that are generated randomly without any annotations in the class, differently in the cases of relational databases ex: PostgreSQL. </p>

<h2> In Databases relational: </h2>
<pre>
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    // Getter and setters
}
</pre>

<h2> In Databases no relational: </h2>
<pre>
@Document(collection = "authors")
public class Author {
    @Id
    private String id;
    
    private String name;
    
    // Getter and setters
}
</pre>

<hr></hr>

<h4> This is a valid example when sending a POST to the end point (http://localhost:8080/api/order) </h4>

<pre>
{
    "orderLineItemsListDto" : [
        {
            "skuCode" : "Iphone",
            "price" : 1200,
            "quantity" : 1
        }
    ]
}
</pre>
