function calInit() {
  var calendarEl = document.getElementById('calendar');
  var calendar = new FullCalendar.Calendar(calendarEl, {
    plugins: [ 'interaction', 'dayGrid', 'timeGrid' ],
    header: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay'
    },
    navLinks: true, // can click day/week names to navigate views
    selectable: true,
    selectMirror: true,
    select: function(arg) {
      var title = prompt('Event Title:');
      if (title) {
        calendar.addEvent({
          title: title,
          start: arg.start,
          end: arg.end,
          allDay: arg.allDay
        })
      }
      calendar.unselect()
    },
    local : "kr",
    editable: true,
    eventLimit: true, // allow "more" link when too many events
    eventSources: [],
    eventClick: function(info) { //만들어진 이벤트를 클릭 했을 시
      console.log(info.event.start + "" + info.event.end + "" + info.event.title );
    }
  });
  events = [
    {
      title: 'The Title', // a property!
      start: '2019-07-11', // a property!
      end: '2019-07-11' // a property! ** see important note below about 'end' **
    },
    {
    title: 'The Title', // a property!
    start: '2019-07-10', // a property!
    end: '2019-07-10' // a property! ** see important note below about 'end' **
    },
  ]
  calendar.addEventSource(events);
  calendar.render();
}
$(function(){
  calInit();

  

})