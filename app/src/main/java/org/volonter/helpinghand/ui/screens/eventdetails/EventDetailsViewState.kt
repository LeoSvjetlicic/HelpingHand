package org.volonter.helpinghand.ui.screens.eventdetails

import org.volonter.helpinghand.ui.common.viewstates.ReviewViewState
import org.volonter.helpinghand.ui.common.viewstates.UserViewState

open class EventDetailsViewState(
    open val id: String,
    open val organisation: UserViewState,
    open val imageLink: String,
    open val title: String,
    open val date: String,
    open val callingNumber: String,
    open val location: String,
    open val description: String,
) {
    companion object {
        val ERROR = EventDetailsViewState(
            "Error",
            UserViewState(
                "https://upload.wikimedia.org/wikipedia/en/b/b1/Portrait_placeholder.png",
                "Error loading",
                "Error loading",
                "Error loading",
            ),
            imageLink = "https://upload.wikimedia.org/wikipedia/en/b/b1/Portrait_placeholder.png",
            title = "Error loading",
            date = "Error loading",
            callingNumber = "Error loading",
            location = "Error loading",
            description = "Error loading"
        )
    }
}

data class FinishedEventDetailsViewState(
    override val id: String = "",
    override val organisation: UserViewState = UserViewState("", "", "", ""),
    override val imageLink: String = "",
    override val title: String = "",
    override val date: String = "",
    override val callingNumber: String = "",
    override val location: String = "",
    override val description: String = "",
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
                "random.lik@gmail.com",
                ""
            )
        ),
        ReviewViewState(
            3,
            "Kako da znam",
            "usbg ksb gusgiu sugbsugsgn sg bsg sg sg bs g sl ig ioshg lishglish glishg lishg i",
            "15.12.2001",
            UserViewState(
                "https://upload.wikimedia.org/wikipedia/commons/7/78/Wikipedia_Profile_picture.jpg",
                "Random lik",
                "random.lik@gmail.com",
                ""
            )
        ), ReviewViewState(
            3,
            "Kako da znam",
            "usbg ksb gusgiu sugbsugsgn sg bsg sg sg bs g sl ig ioshg lishglish glishg lishg i",
            "15.12.2001",
            UserViewState(
                "https://upload.wikimedia.org/wikipedia/commons/7/78/Wikipedia_Profile_picture.jpg",
                "Random lik",
                "random.lik@gmail.com",
                ""
            )
        ), ReviewViewState(
            3,
            "Kako da znam",
            "usbg ksb gusgiu sugbsugsgn sg bsg sg sg bs g sl ig ioshg lishglish glishg lishg i",
            "15.12.2001",
            UserViewState(
                "https://upload.wikimedia.org/wikipedia/commons/7/78/Wikipedia_Profile_picture.jpg",
                "Random lik",
                "random.lik@gmail.com",
                ""
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
                "random.lik@gmail.com",
                ""
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
                "random.lik@gmail.com",
                ""
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
                "random.lik@gmail.com",
                ""
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
                "random.lik@gmail.com",
                ""
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
                "random.lik@gmail.com",
                ""
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
                "random.lik@gmail.com",
                ""
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
                "random.lik@gmail.com",
                ""
            )
        )
    )
) : EventDetailsViewState(
    id,
    organisation,
    imageLink,
    title,
    date,
    callingNumber,
    location,
    description
)

data class UnfinishedEventDetailsViewState(
    override val id: String = "",
    override val organisation: UserViewState = UserViewState("", "", "", ""),
    override val imageLink: String = "",
    override val title: String = "",
    override val date: String = "",
    override val callingNumber: String = "",
    override val location: String = "",
    override val description: String = "",
    val isUserApplied: Boolean = false,
    val neededVolunteers: Int = 12,
    val appliedVolunteers: List<String> = emptyList()
) : EventDetailsViewState(
    id,
    organisation,
    imageLink,
    title,
    date,
    callingNumber,
    location,
    description
)
