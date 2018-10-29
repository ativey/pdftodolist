<html>
<h1>ToDoPDF</h1>

<p>
    <a href="category">Category</a>
    <a href="generatePdf">PDF</a>
</p>

<h1>Generate PDF</h1>
<form method="post" action="/generatePdf">
    <input type="checkbox" name="showLabels" checked>Show labels
    <select name="strategy">
    <option value="">Web default</option>
    <option value="NONE">NONE</option>
    <option value="IN_LINE">IN_LINE</option>
    <option value="END_OF_CATEGORY">END_OF_CATEGORY</option>
    <option value="END_OF_LIST">END_OF_LIST</option>
    <option value="FRED">FRED</option>
</select>
    <button type="submit">Generate PDF</button>
</form>

</html>
