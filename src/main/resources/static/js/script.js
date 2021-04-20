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

function fcaleatoire() {
     let codeHTML = "";
     let BPM="";
     for (let i = 0; i < 10; i++) {
         BPM="";
         let fcAl = Math.round(170 * Math.random() + 50);
         codeHTML += "<tr><td>" + fcAl + "</td></tr>";
         BPM+= " "+ fcAl + " ";
  }
  document.getElementById("bpm").innerHTML = BPM;
  document.getElementById("tabfc").innerHTML = codeHTML;
 
        if (BPM >= 200) {

            alert("Votre fréquence cardiaque est trop élevée vous faites peut être une crise stéréotypée");
        }
        if (BPM <= 60) {
            alert("Attention votre fréquence cardiaque est trop basse ");
        }
 }
fcaleatoire();