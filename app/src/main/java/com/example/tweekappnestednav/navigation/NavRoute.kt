package com.example.tweekappnestednav.navigation

sealed class  NavRoute(val path: String) {

    object Login : NavRoute("login")

    object Otp : NavRoute("otp")

    object EnterNameScreen : NavRoute("enter_name")

    object DobScreen: NavRoute("dob")

    object GenderScreen: NavRoute("gender_screen")

    object WeightScreen: NavRoute("weight_screen")

    object HeightScreen: NavRoute("height_screen")

    object HandednessScreen: NavRoute("handedness_screen")

    object ProfilePic: NavRoute("profile_pic")

    object AdminHome: NavRoute("admin_home")

    object SessionsScreen: NavRoute("sessions_screen")

    object KitsScreen: NavRoute("kits_screen")

    object GroupsScreen: NavRoute("groups_screen")

    object JoinOrgScreen: NavRoute("join_org_screen")




    // build navigation path (for screen navigation)
    fun withArgs(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach{ arg ->
                append("/$arg")
            }
        }
    }

    // build and setup route format (in navigation graph)
    fun withArgsFormat(vararg args: String) : String {
        return buildString {
            append(path)
            args.forEach{ arg ->
                append("/{$arg}")
            }
        }
    }
}


