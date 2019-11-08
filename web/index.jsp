<%@ page import="beans.Point" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="array" class="beans.PointBean" scope="application"/>
<html>
<head>
    <title>Title</title>
    <style>
        input[type=checkbox] {
            cursor: pointer;
            width: 15px;
            height: 15px;
        }

        html {
            min-width: 1200px;
        }

        .form {
            margin-top: 50px;
            margin-left: 10%;
            float: left;
        }

        #graphCanvas {
            margin-top: 50px;
            margin-right: 10%;
            float: right;
        }

        header {
            text-align: center;
            font-size: 36px;
            font-family: "Times New Roman", serif;
        }

        .container {
            height: 500px;
            width: 80%;
            margin-left: 10%;
            margin-right: 10%;
            margin-top: 1%;
            box-shadow: 0 0 5px 2px grey;
            background-color: #f6f6f6;
            text-align: center;
            float: left;
            border: 3px solid grey;
            border-radius: 50px;
        }

        select {
            box-shadow: 0 0 2px 1px grey;
            border-style: outset;
            align-self: center;
            /*-webkit-appearance: none;*/
            /*-moz-appearance: none;*/
            /*appearance: none;*/
            border-width: 2px;
            height: 30px;
            width: 50px;
            background-color: lightgray;
            border-color: lightgray;
            border-radius: 7px;
        }

        #textarea {
            box-shadow: 0 0 2px 1px grey;
            border-width: 2px;
            border-color: lightgray;
            border-style: outset;
            height: 30px;
            background-color: lightgray;
            width: 50px;
            align-self: center;
            text-align: center;
            border-radius: 7px;
        }

        #Y {
            vertical-align: center;
        }

        button {
            cursor: pointer;
            box-shadow: 0 0 2px 1px grey;
            border-width: 2px;
            border-color: lightgray;
            align-self: center;
            height: 30px;
            background-color: lightgray;
            border-radius: 7px;
            width: 50px;
        }

        #accept {
            box-shadow: 0 0 2px 1px grey;
            border-width: 2px;
            border-color: lightgray;
            border-style: outset;
            align-self: center;
            background-color: lightgray;
            border-radius: 7px;
            height: 30px;
            width: 200px;
        }

        table {
            align-self: center;
            background-color: azure;
            font-size: 20px;
            border: 1px solid grey;
            border-radius: 7px;
            column-gap: 2px;
            column-rule: 7px grey;
            width: 50%;
            margin-left: 25%;
            margin-right: 25%;
            margin-top: 20%;
        }

        td, th {
            text-align: center;
            background-color: lightblue;
            width: 5%;
        }

        tr {
            background-color: lightyellow;
        }

        .table {
            padding-top: 12%;
        }
    </style>
</head>
<body>
<header> Окишор Александр, Р3200, Вариант 200026</header>
<main>
    <div class="container">
        <div class="canvas">
            <canvas id="graphCanvas" width="400" height="400">Ваш браузер не поддерживает Canvas</canvas>
            <script>
                var drawingCanvas = document.getElementById("graphCanvas");
                var context = drawingCanvas.getContext("2d");
                drawCanvas("R");

                drawingCanvas.addEventListener('click', function (ev) {
                    let R = document.getElementById("R").value;
                    if (R === "") {
                        R = "R";
                    }
                    drawCanvas(R);
                    let r = parseFloat(R).toFixed(3);
                    if (isNaN(r)) {
                        document.getElementById("R").style = 'background-color: #ff8282;';
                        let incr = 1;
                        while (incr < document.getElementById("R").options.length) {
                            document.getElementById("R").options.item(incr).style = 'background-color: lightblue;';
                            incr++;
                        }
                    } else {

                        context.strokeStyle = 'rgb(255,0,0)';
                        context.fillStyle = 'rgb(255,0,0)';
                        context.beginPath();
                        context.arc(ev.offsetX, ev.offsetY, 2, 0, 2 * Math.PI);
                        context.closePath();
                        context.fill();

                        let x = ((ev.layerX - drawingCanvas.width / 2) / 140 * r).toPrecision(3);
                        let y = ((drawingCanvas.height / 2 - ev.layerY) / 140 * r).toPrecision(3);
                        //todo добавить добавление этих единиц в соответствующие поля
                        document.getElementById("X").value = String(x);
                        document.getElementById("Y").innerText = String(y);
                    }
                });

                function drawCanvas(r) {
                    context.clearRect(0, 0, 400, 400);
                    context.strokeStyle = 'rgb(0,0,0)';
                    context.strokeRect(0, 0, 400, 400);
                    context.lineWidth = 2;
                    context.font = "small-caps 12px Arial";

                    //отрисовка области
                    //todo отрисовка областей
                    context.fillStyle = 'rgb(0,155,255)';
                    context.fillRect(200, 130, 140, 70);
                    // шаблон треугольника
                    context.beginPath();
                    context.moveTo(200, 200);
                    context.lineTo(60, 200);
                    context.lineTo(200, 60);
                    context.lineTo(200, 200);
                    context.closePath();
                    context.fill();
                    // шаблон треугольника

                    //шаблон сектора круга
                    context.beginPath();
                    context.moveTo(200, 200);
                    context.lineTo(200, 100);
                    context.arc(200, 200, 70, Math.PI / 2, Math.PI, false);//x и y центра, радиус, начальная точка, конечная точка , против часовой стрелки
                    context.lineTo(200, 200);
                    context.closePath();
                    context.fill();
                    // шаблон сектора круга
                    context.fillStyle = 'rgb(0,0,0)';
                    //todo зависит от варианта

                    // ось  у
                    context.beginPath();
                    context.moveTo(200, 400);
                    context.lineTo(200, 0);
                    context.closePath();
                    context.stroke();

                    // стрелка оси у
                    context.beginPath();
                    context.moveTo(200, 0);
                    context.lineTo(195, 10);
                    context.moveTo(200, 0);
                    context.lineTo(205, 10);
                    context.closePath();
                    context.stroke();

                    // ось х
                    context.beginPath();
                    context.moveTo(0, 200);
                    context.lineTo(400, 200);
                    context.closePath();
                    context.stroke();

                    // стрелки оси х
                    context.beginPath();
                    context.moveTo(400, 200);
                    context.lineTo(390, 195);
                    context.moveTo(400, 200);
                    context.lineTo(390, 205);
                    context.closePath();
                    context.stroke();

                    // буквы х и у
                    context.fillText("X", 387, 190);
                    context.fillText("Y", 210, 13);

                    // засечики на оси X
                    context.beginPath();
                    context.moveTo(270, 195);
                    context.lineTo(270, 205);
                    context.moveTo(340, 195);
                    context.lineTo(340, 205);
                    context.closePath();
                    context.stroke();

                    context.beginPath();
                    context.moveTo(130, 195);
                    context.lineTo(130, 205);
                    context.moveTo(60, 195);
                    context.lineTo(60, 205);
                    context.closePath();
                    context.stroke();

                    // засечки на оси Y
                    context.beginPath();
                    context.moveTo(195, 130);
                    context.lineTo(205, 130);
                    context.moveTo(195, 60);
                    context.lineTo(205, 60);
                    context.closePath();
                    context.stroke();

                    context.beginPath();
                    context.moveTo(195, 270);
                    context.lineTo(205, 270);
                    context.moveTo(195, 340);
                    context.lineTo(205, 340);
                    context.closePath();
                    context.stroke();

                    // пометки значений засечек
                    let halfR;
                    if (r === "R") {
                        halfR = "R/2"
                    } else {
                        halfR = r / 2;
                    }
                    context.fillText(halfR, 263, 190);
                    context.fillText(halfR, 210, 133);
                    context.fillText(r, 210, 63);
                    context.fillText(r, 337, 190);
                    context.fillText("-" + halfR, 120, 190);
                    context.fillText("-" + halfR, 210, 273);
                    context.fillText("-" + r, 55, 190);
                    context.fillText("-" + r, 210, 343);

                }

                function setR(r) {
                    document.getElementById("R").style = 'background-color: lightblue;';
                    document.getElementById("R").options.remove(0);
                    drawCanvas(r);
                }

                function textareaCheck() {
                    let input = document.getElementById("textarea").value;
                    if (input.indexOf(',') !== -1) {
                        input = input.replace(',', '.');
                        document.getElementById("textarea").value = input.toString();
                    }
                }

                function validation() {
                    let textarea = document.getElementById("textarea").value
                }
            </script>
        </div>
        <div class="form">
            <form action="controllerServlet" method="get">
                <p>Координата X</p>
                <label>
                    <input type="checkbox" name="checkbox[]" value="-3"/>-3
                </label>
                <label>
                    <input type="checkbox" name="checkbox[]" value="-2"/>-2
                </label>
                <label>
                    <input type="checkbox" name="checkbox[]" value="-1"/>-1
                </label>
                <label>
                    <input type="checkbox" name="checkbox[]" value="0"/>0
                </label>
                <label>
                    <input type="checkbox" name="checkbox[]" value="1"/>1
                </label>
                <label>
                    <input type="checkbox" name="checkbox[]" value="2"/>2
                </label>
                <label>
                    <input type="checkbox" name="checkbox[]" value="3"/>3
                </label>
                <label>
                    <input type="checkbox" name="checkbox[]" value="4"/>4
                </label>
                <label>
                    <input type="checkbox" name="checkbox[]" value="5"/>5
                </label>
                <p>Координата Y</p>
                <label for="textarea"></label><input type="text" placeholder="-3...5" name="textarea" id="textarea"
                                                     onkeydown="if(event.keyCode===13){return false;}"
                                                     oninput="textareaCheck()">
                <p>Координата R:</p>
                <label for="R"></label>
                <select name="select" onchange="setR(this.options[this.selectedIndex].value)" id="R" required>
                    <option value="R" selected disabled>R</option>
                    <option value="1">1</option>
                    <option value="1.5">1.5</option>
                    <option value="2">2</option>
                    <option value="2.5">2.5</option>
                    <option value="3">3</option>
                </select>
                <p><input type="submit" id="accept" value="Применить"></p>
            </form>
        </div>
    </div>
    <div class="table">
        <table>
            <%
                StringBuilder builder = new StringBuilder();
                builder.append("<tr>");
                builder.append("<th>").append("X").append("</th>");
                builder.append("<th>").append("Y").append("</th>");
                builder.append("<th>").append("R").append("</th>");
                builder.append("<th>").append("Result").append("</th>");
                builder.append("</tr>");
                for (Point point : array.getArray()) {
                    builder.append("<tr>");
                    builder.append("<td>").append(point.getX()).append("</td>");
                    builder.append("<td>").append(point.getY()).append("</td>");
                    builder.append("<td>").append(point.getR()).append("</td>");
                    if (point.isHit()) {
                        builder.append("<td style='background-color: lightgreen;'>").append(point.isHit()).append("</td>");
                    } else {
                        builder.append("<td style='background-color: lightcoral;'>").append(point.isHit()).append("</td>");
                    }
                    builder.append("</tr>");
                }
                out.println(builder.toString());
            %>
        </table>
    </div>
</main>
</body>
</html>
