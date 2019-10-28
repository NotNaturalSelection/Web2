<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        html{
            min-width: 650px;
        }
        .form{
            margin-top: 50px;
            margin-left: 10%;
            float: left;
        }
        #graphCanvas{
            margin-top: 50px;
            margin-right: 10%;
            float: right;
        }
        header {
            text-align: center;
            font-size: 36px;
            font-family: "Times New Roman";
        }
        .container{
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
        select{
            box-shadow: 0 0 2px 1px grey;
            border-style: outset;
            align-self: center;
            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
            border-width: 2px;
            height: 30px;
            width: 50px;
            background-color: lightgray;
            border-color: lightgray;
            border-radius: 7px;
        }
        #textarea{
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
        #Y{
            vertical-align: center;
        }
        button{
            box-shadow: 0 0 2px 1px grey;
            border-width: 2px;
            border-color: lightgray;
            align-self: center;
            height: 30px;
            background-color: lightgray;
            border-radius: 7px;
            width: 50px;
        }
        #accept{
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
    </style>
</head>
<body>
<header> Окишор Александр, Р3200, Вариант -...</header>
<main>
    <div class="container">
        <div class="canvas" >
            <canvas id="graphCanvas" width="400" height="400">Ваш браузер не поддерживает Canvas</canvas>
            <script>
                var drawingCanvas = document.getElementById("graphCanvas");
                var context = drawingCanvas.getContext("2d");
                drawCanvas("R");

                drawingCanvas.addEventListener('click', function (ev) {
                    let R = document.getElementById("R").value;
                    if(R === ""){
                        R = "R";
                    }
                    drawCanvas(R);
                    context.strokeStyle = 'rgb(255,0,0)';
                    context.strokeRect(ev.layerX, ev.layerY, 1, 1);
                    document.getElementById("X").value = String(ev.layerX);
                    document.getElementById("Y").value = String(ev.layerY);
                });

                function drawCanvas(r) {
                    context.clearRect(0, 0, 400, 400);
                    context.strokeStyle = 'rgb(0,0,0)';
                    context.strokeRect(0, 0, 400, 400);
                    context.lineWidth = 2;
                    context.font = "small-caps 12px Arial";

                    //отрисовка области
                    //todo отрисовка областей
                    context.fillStyle = 'rgb(0,125,255)';
                    context.fillRect(130, 200, 70, 140);
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
                    if(r === "R"){
                        halfR = "R/2"
                    }else{
                        halfR = r/2;
                    }
                    context.fillText(halfR, 263, 190);
                    context.fillText(halfR, 210, 133);
                    context.fillText(r, 210, 63);
                    context.fillText(r, 337, 190);
                    context.fillText("-"+halfR, 120, 190);
                    context.fillText("-" + halfR, 210, 273);
                    context.fillText("-"+r, 55, 190);
                    context.fillText("-"+r, 210, 343);

                }
                function setR(r){
                    document.getElementById("R").value = r;
                    drawCanvas(r);
                }
            </script>
        </div>
        <div class="form">
            <p>Координата X</p>
            <p>
                <select name="X" id="X">
                    <option value="-4">-4</option>
                    <option value="-3">-3</option>
                    <option value="-2">-2</option>
                    <option value="-1">-1</option>
                    <option value="0">0</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                </select>
            </p>
            <p>Координата Y</p>
            <label for="textarea"></label><input type="text" placeholder="-5...5" name="textarea" id="textarea"
                                                 onchange="textAreaHandler()"
                                                 onkeydown="if(event.keyCode===13){return false;}">
            <p>Значение Y:</p>
            <p id="Y">?</p>
            <p>Координата R</p>
            <button type="button" onclick="setR(1)" name="button1" id="button1" value="1">1</button>
            <button type="button" onclick="setR(2)" name="button2" id="button2" value="2">2</button>
            <button type="button" onclick="setR(3)" name="button3" id="button3" value="3">3</button>
            <button type="button" onclick="setR(4)" name="button4" id="button4" value="4">4</button>
            <button type="button" onclick="setR(5)" name="button5" id="button5" value="5">5</button>
            <textarea name="R" id="R" cols="1" rows="1" maxlength="1" hidden readonly></textarea>
            <p><input type="button" id="accept" value="Применить"></p>
        </div>
    </div>
</main>
</body>
</html>
