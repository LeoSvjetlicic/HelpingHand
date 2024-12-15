package org.volonter.helpinghand.ui.screens.eventdetails

import org.volonter.helpinghand.ui.common.viewstates.ReviewViewState
import org.volonter.helpinghand.ui.common.viewstates.UserViewState

sealed class EventDetailsViewState(
    val id: String = "",
    val organisation: UserViewState = UserViewState(
        imageLink = "https://upload.wikimedia.org/wikipedia/commons/8/8d/Frog_on_palm_frond.jpg",
        name = "Udruga Breza",
        email = "udruga.breza@gmail.com"
    ),
    val imageLink: String = "https://upload.wikimedia.org/wikipedia/commons/8/8d/Frog_on_palm_frond.jpg",
    val title: String = "Udruga Breza - Volonterska akcija",
    val date: String = "15.12.2001",
    val callingNumber: String = "031 272 818",
    val location: String = "Ul. Dobrise Cesarica 10, 31000, Osijek",
    val description: String = "Udruga Breza Osijek posvećena je pružanju podrške osobama s invaliditetom. Ova volonterska akcija omogućava svim zainteresiranim osobama da se uključe u različite aktivnosti, kao što su organizacija događanja, pomoć pri vođenju administracije, te aktivno sudjelovanje u projektima koji promiču inkluziju i ravnopravnost. Pridružite se i pomozite stvarati bolju zajednicu za sve!",
)

data class FinishedEventDetailsViewState(
    val reviewsPerPage: Int = 5,
    val currentPage: Int = 1,
    val allReviews: List<ReviewViewState> = listOf(
        ReviewViewState(
            3,
            "Kako da znam",
            "usbg ksb gusgiu sugbsugsgn sg bsg sg sg bs g sl ig ioshg lishglish glishg lishg i",
            "15.12.2001",
            UserViewState(
                "https://upload.wikimedia.org/wikipedia/commons/7/78/Wikipedia_Profile_picture.jpg",
                "Random lik",
                "random.lik@gmail.com"
            )
        ), ReviewViewState(
            3,
            "Kako da znam",
            "usbg ksb gusgiu sugbsugsgn sg bsg sg sg bs g sl ig ioshg lishglish glishg lishg i",
            "15.12.2001",
            UserViewState(
                "https://upload.wikimedia.org/wikipedia/commons/7/78/Wikipedia_Profile_picture.jpg",
                "Random lik",
                "random.lik@gmail.com"
            )
        ), ReviewViewState(
            3,
            "Kako da znam",
            "usbg ksb gusgiu sugbsugsgn sg bsg sg sg bs g sl ig ioshg lishglish glishg lishg i",
            "15.12.2001",
            UserViewState(
                "https://upload.wikimedia.org/wikipedia/commons/7/78/Wikipedia_Profile_picture.jpg",
                "Random lik",
                "random.lik@gmail.com"
            )
        ),
        ReviewViewState(
            5,
            "Kako da znam",
            "aifhsidufh wofhseiuf suifh bsfbsi bfksubfiusbfkshflsig hoh gksuh su hgsouhg surg sukgh uis bg usbg ksb gusgiu sugbsugsgn sg bsg sg sg bs g sl ig ioshg lishglish glishg lishg i",
            "15.12.2001",
            UserViewState(
                "https://upload.wikimedia.org/wikipedia/commons/7/78/Wikipedia_Profile_picture.jpg",
                "Random lik",
                "random.lik@gmail.com"
            )
        ),
        ReviewViewState(
            1,
            "Kako da znam",
            "aifhsidufh wofhseiuf suifh bsfbsi bfksubfiusbfkshflsig hoh gksuh su hgsouhg surg sukgh uis bg usbg ksb gusgiu sugbsugsgn sg bsg sg sg bs g sl ig ioshg lishglish glishg lishg i",
            "15.12.2001",
            UserViewState(
                "https://upload.wikimedia.org/wikipedia/commons/7/78/Wikipedia_Profile_picture.jpg",
                "Random lik",
                "random.lik@gmail.com"
            )
        ),
        ReviewViewState(
            0,
            "Kako da znam",
            "aifhsidufh wofhseiuf suifh bsfbsi bfksubfiusbfkshflsig hoh gksuh su hgsouhg surg sukgh uis bg usbg ksb gusgiu sugbsugsgn sg bsg sg sg bs g sl ig ioshg lishglish glishg lishg i",
            "15.12.2001",
            UserViewState(
                "https://upload.wikimedia.org/wikipedia/commons/7/78/Wikipedia_Profile_picture.jpg",
                "Random lik",
                "random.lik@gmail.com"
            )
        ),
        ReviewViewState(
            0,
            "Kako da znam",
            "aifhsidufh wofhseiuf suifh bsfbsi bfksubfiusbfkshflsig hoh gksuh su hgsouhg surg sukgh uis bg usbg ksb gusgiu sugbsugsgn sg bsg sg sg bs g sl ig ioshg lishglish glishg lishg i",
            "15.12.2001",
            UserViewState(
                "https://upload.wikimedia.org/wikipedia/commons/7/78/Wikipedia_Profile_picture.jpg",
                "Random lik",
                "random.lik@gmail.com"
            )
        ),
        ReviewViewState(
            0,
            "Kako da znam",
            "aifhsidufh wofhseiuf suifh bsfbsi bfksubfiusbfkshflsig hoh gksuh su hgsouhg surg sukgh uis bg usbg ksb gusgiu sugbsugsgn sg bsg sg sg bs g sl ig ioshg lishglish glishg lishg i",
            "15.12.2001",
            UserViewState(
                "https://upload.wikimedia.org/wikipedia/commons/7/78/Wikipedia_Profile_picture.jpg",
                "Random lik",
                "random.lik@gmail.com"
            )
        ),
        ReviewViewState(
            0,
            "Kako da znam",
            "aifhsidufh wofhseiuf suifh bsfbsi bfksubfiusbfkshflsig hoh gksuh su hgsouhg surg sukgh uis bg usbg ksb gusgiu sugbsugsgn sg bsg sg sg bs g sl ig ioshg lishglish glishg lishg i",
            "15.12.2001",
            UserViewState(
                "https://upload.wikimedia.org/wikipedia/commons/7/78/Wikipedia_Profile_picture.jpg",
                "Random lik",
                "random.lik@gmail.com"
            )
        ),
        ReviewViewState(
            0,
            "Kako da znam",
            "aifhsidufh wofhseiuf suifh bsfbsi bfksubfiusbfkshflsig hoh gksuh su hgsouhg surg sukgh uis bg usbg ksb gusgiu sugbsugsgn sg bsg sg sg bs g sl ig ioshg lishglish glishg lishg i",
            "15.12.2001",
            UserViewState(
                "https://upload.wikimedia.org/wikipedia/commons/7/78/Wikipedia_Profile_picture.jpg",
                "Random lik",
                "random.lik@gmail.com"
            )
        )
    )
) : EventDetailsViewState()

data class UnfinishedEventDetailsViewState(
    val isUserApplied: Boolean = false,
    val neededVolunteers: Int = 12,
    val appliedVolunteers: Int = 10
) : EventDetailsViewState()
