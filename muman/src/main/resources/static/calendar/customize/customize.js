document.addEventListener('DOMContentLoaded', function() {
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
      editable: true,
      eventLimit: true, // allow "more" link when too many events
      events: [
        { // this object will be "parsed" into an Event Object
          title: 'The Title', // a property!
          start: '2019-07-06', // a property!
          end: '2019-07-12' // a property! ** see important note below about 'end' **
        },
      ],
      eventClick: function(info) { //만들어진 이벤트를 클릭 했을 시
        console.log(info.event.start + "" + info.event.end + "" + info.event.title );
      },
      locale : "kr",
    });

    calendar.render();
  });