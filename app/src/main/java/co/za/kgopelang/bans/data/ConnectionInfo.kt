package co.za.kgopelang.bans.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import com.google.gson.annotations.SerializedName


data class ConnectionInfo (

    @SerializedName("success" ) var success : Boolean? = null,
    @SerializedName("result"  ) var result  : Result?  = Result()

)



data class Beneficiary (

    @SerializedName("FullName"     ) var FullName     : String?  = null,
    @SerializedName("IDNo"         ) var IDNo         : String?  = null,
    @SerializedName("Relationship" ) var Relationship : String?  = null,
    @SerializedName("IsPrimary"    ) var IsPrimary    : Boolean? = null

)

data class Service (

    @SerializedName("Title"                 ) var Title                 : String?           = null,
    @SerializedName("Subtitle"              ) var Subtitle              : String?           = null,
    @SerializedName("ConnectionType"        ) var ConnectionType        : String?           = null,
    @SerializedName("ServiceType"           ) var ServiceType           : String?           = null,
    @SerializedName("FUP"                   ) var FUP                   : Int?              = null,
    @SerializedName("AP"                    ) var AP                    : Int?              = null,
    @SerializedName("Capped"                ) var Capped                : Boolean?          = null,
    @SerializedName("Quota"                 ) var Quota                 : Int?              = null,
    @SerializedName("Usage"                 ) var Usage                 : Double?           = null,
    @SerializedName("TopUp"                 ) var TopUp                 : Double?           = null,
    @SerializedName("TopUpExpiry"           ) var TopUpExpiry           : String?           = null,
    @SerializedName("NightOwlRemaining"     ) var NightOwlRemaining     : Int?              = null,
    @SerializedName("NightOwlUsage"         ) var NightOwlUsage         : Double?           = null,
    @SerializedName("NightOwlExpiry"        ) var NightOwlExpiry        : String?           = null,
    @SerializedName("NightOwlActivated"     ) var NightOwlActivated     : Boolean?          = null,
    @SerializedName("SocialActivated"       ) var SocialActivated       : String?           = null,
    @SerializedName("SocialRemaining"       ) var SocialRemaining       : String?           = null,
    @SerializedName("SocialUsage"           ) var SocialUsage           : String?           = null,
    @SerializedName("SocialExpiry"          ) var SocialExpiry          : String?           = null,
    @SerializedName("NetflixActivated"      ) var NetflixActivated      : String?           = null,
    @SerializedName("NetflixRemaining"      ) var NetflixRemaining      : String?           = null,
    @SerializedName("NetflixUsage"          ) var NetflixUsage          : String?           = null,
    @SerializedName("NetflixExpiry"         ) var NetflixExpiry         : String?           = null,
    @SerializedName("YoutubeActivated"      ) var YoutubeActivated      : String?           = null,
    @SerializedName("YoutubeRemaining"      ) var YoutubeRemaining      : String?           = null,
    @SerializedName("YoutubeUsage"          ) var YoutubeUsage          : String?           = null,
    @SerializedName("YoutubeExpiry"         ) var YoutubeExpiry         : String?           = null,
    @SerializedName("ServiceKey"            ) var ServiceKey            : Int?              = null,
    @SerializedName("Login"                 ) var Login                 : String?           = null,
    @SerializedName("ModemSerialNumber"     ) var ModemSerialNumber     : String?           = null,
    @SerializedName("AccountName"           ) var AccountName           : String?           = null,
    @SerializedName("NotificationStatus"    ) var NotificationStatus    : String?           = null,
    @SerializedName("NotificationInfo"      ) var NotificationInfo      : String?           = null,
    @SerializedName("NotificationMechanism" ) var NotificationMechanism : String?           = null,
    @SerializedName("ParentServiceKey"      ) var ParentServiceKey      : String?           = null,
    @SerializedName("ChildServices"         ) var ChildServices         : ArrayList<String> = arrayListOf(),
    @SerializedName("Created"               ) var Created               : String?           = null,
    @SerializedName("Expiry"                ) var Expiry                : String?           = null,
    @SerializedName("SuspendReason"         ) var SuspendReason         : String?           = null,
    @SerializedName("StatusEnum"            ) var StatusEnum            : Int?              = null,
    @SerializedName("LitID"                 ) var LitID                 : String?           = null,
    @SerializedName("ServiceID"             ) var ServiceID             : String?           = null,
    @SerializedName("IPAddress"             ) var IPAddress             : String?           = null,
    @SerializedName("Status"                ) var Status                : String?           = null,
    @SerializedName("Icon"                  ) var Icon                  : String?           = null,
    @SerializedName("Beneficiary"           ) var Beneficiary           : Beneficiary?      = Beneficiary(),
    @SerializedName("IsChild"               ) var IsChild               : Boolean?          = null

)

data class Result (

    @SerializedName("service" ) var service : Service? = Service()

)

