<h2>Task Overview</h2>
<p>We are nearing the completion of this course, and to finalize our application, I encourage you to finish writing all unit tests for the controller and service in our application.</p>

<h3>Task Details</h3>
<p>Your job is to test all the methods in the controller and service that haven't yet been covered by tests. To accomplish this, I suggest using JUnit and the Mockito library.</p>
<p><strong>Note:</strong> I've already written a few tests for methods in the controller and service, so you'll have additional examples to reference when implementing the remaining tests.</p>

<h2>Repository Testing</h2>

<h3>Existing Tests</h3>
<p>There are already tests written for the <code>findAllBooks</code>, <code>createBook</code>, and <code>updateBook</code> methods. You'll need to write tests for the other methods. Below are some guidelines for testing the remaining methods.</p>

<h3>deleteBooks(String id)</h3>
<ul>
    <li>Ensure that the delete method is called with the correct book ID.</li>
    <li>Test the error handling when trying to delete a book that does not exist.</li>
    <li>On a successful deletion, verify only the status code.</li>
    <li>When an error occurs, check both the status code and the error message.</li>
</ul>

<h3>findByAuthor(String author)</h3>
<ul>
    <li>Verify that a list of books is returned for the specified author.</li>
    <li>Ensure that the method handles cases where no books are found for the given author.</li>
</ul>

<h2>Service Testing</h2>

<h3>findByAuthor(String author)</h3>
<ul>
    <li>Ensure that a list of books is returned for the specified author.</li>
    <li>Test the scenario where no books exist for the given author, and confirm that an empty list is returned.</li>
</ul>

<h3>deleteBook(String id)</h3>
<ul>
    <li>Ensure that the delete method is called with the correct book ID.</li>
    <li>Test the error handling when the book with the specified ID is not found.</li>
</ul>
