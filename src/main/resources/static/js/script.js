/* global google */

function makeGraph(container, labels) {
  container = document.getElementById(container);
  labels = document.getElementById(labels);
  var dnl = container.getElementsByTagName("li");
  for (var i = 0; i < dnl.length; i++) {
    var item = dnl.item(i);
    var value = item.innerHTML;
    var color = (item.style.background = color);
    var content = value.split(":");
    value = content[0];
    item.style.height = value + "px";
    item.style.top = 199 - value * 10 + "px";
    item.style.left = i * 50 + 20 + "px";
    item.style.height = value * 10 + "px";
    item.innerHTML = value;
    item.style.visibility = "visible";
    color = content[2];
    if (color !== false) item.style.background = color;
    labels.innerHTML =
      labels.innerHTML +
      "<span style='margin:8px;background:" +
      color +
      "'>" +
      content[1] +
      "</span>";
  }
}

window.onload = function () {
  makeGraph("graph", "labels");
};


google.charts.load('current', {packages: ['corechart', 'line']});
google.charts.setOnLoadCallback(drawTrendlines);

function drawTrendlines() {
      var data = new google.visualization.DataTable();
      data.addColumn('number', 'X');
      data.addColumn('number', 'Fréquence cardiaque');
      data.addColumn('number', 'Limite basse');
      data.addColumn('number', 'Limite haute');
      let BPM="";
      for (let i=0;i<61;i++){
          BPM="";
          let fcAl = Math.round(170 * Math.random() + 50);
          data.addRows([[i, fcAl, 50, 220]]);
          BPM+= " "+ fcAl + " ";
      }
      document.getElementById("bpm").innerHTML = BPM;
      if (BPM >= 200) {

            alert("Votre fréquence cardiaque est trop élevée vous faites peut être une crise stéréotypée");
        }
        if (BPM <= 60) {
            alert("Attention votre fréquence cardiaque est trop basse ");
        }

      var options = {
        hAxis: {
          title: 'Temps en s'
        },
        vAxis: {
          title: 'Rythme cardiaque en bpm'
        },
        colors:['#004b96', '#7ca8d4', '#7ca8d4'],
        series: { 
        	0: {
            lineWidth: 3,
          },
        	1: {
            lineWidth: 1,
          },
          2: {
            lineWidth: 2,
          }
         
        }
      };

      var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
      chart.draw(data, options);
    }