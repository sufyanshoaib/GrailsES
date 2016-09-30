class BootStrap {

    def eventService

    def init = { servletContext ->
        eventService.addEvent("Expo Pakistan", "2 days expo of Pakistan, all stuffs...at expo center")
        eventService.addEvent("New Year Even", "Enjoy new year night at beach club")
        eventService.addEvent("Children's Day", "Kids play, activities, sports, fun, family")
        eventService.addEvent("Education Expo", "3 days Eduction expo, college, schools at expo center...")
        eventService.addEvent("Arena sports area", "All year open, indoor activities, bowling, snooker, ice skating")
        eventService.addEvent("All you can eat", "All you can eat offer is back in this ramadan at Pizza Hut")

    }
    def destroy = {
    }
}
