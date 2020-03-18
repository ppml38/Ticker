import java.lang.Integer;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.io.*;
import javax.imageio.*;

public class Ticker implements ActionListener
{


        String[] HOURS = { "Hour", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" };
        String[] MINUTES = { "Minute", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
                        "24", "25", "26", "27", "28", "29", "30", "31", "32", "33","34", "35", "36", "37", "38", "39", "40", "41", "42", "43","44", "45", "46", "47", "48", "49", "50", "51", "52", "53",
                        "54", "55", "56", "57", "58", "59"                  };
		//String[] TIME_ZONES = {"Etc/GMT+12", "Etc/GMT+11", "MIT", "Pacific/Apia", "Pacific/Midway", "Pacific/Niue", "Pacific/Pago_Pago", "Pacific/Samoa", "US/Samoa", "America/Adak", "America/Atka", "Etc/GMT+10", "HST", "Pacific/Fakaofo", "Pacific/Honolulu", "Pacific/Johnston", "Pacific/Rarotonga", "Pacific/Tahiti", "SystemV/HST10", "US/Aleutian", "US/Hawaii", "Pacific/Marquesas", "AST", "America/Anchorage", "America/Juneau", "America/Nome", "America/Yakutat", "Etc/GMT+9", "Pacific/Gambier", "SystemV/YST9", "SystemV/YST9YDT", "US/Alaska", "America/Dawson", "America/Ensenada", "America/Los_Angeles", "America/Tijuana", "America/Vancouver", "America/Whitehorse", "Canada/Pacific", "Canada/Yukon", "Etc/GMT+8", "Mexico/BajaNorte", "PST", "PST8PDT", "Pacific/Pitcairn", "SystemV/PST8", "SystemV/PST8PDT", "US/Pacific", "US/Pacific-New", "America/Boise", "America/Cambridge_Bay", "America/Chihuahua", "America/Dawson_Creek", "America/Denver", "America/Edmonton", "America/Hermosillo", "America/Inuvik", "America/Mazatlan", "America/Phoenix", "America/Shiprock", "America/Yellowknife", "Canada/Mountain", "Etc/GMT+7", "MST", "MST7MDT", "Mexico/BajaSur", "Navajo", "PNT", "SystemV/MST7", "SystemV/MST7MDT", "US/Arizona", "US/Mountain", "America/Belize", "America/Cancun", "America/Chicago", "America/Costa_Rica", "America/El_Salvador", "America/Guatemala", "America/Managua", "America/Menominee", "America/Merida", "America/Mexico_City", "America/Monterrey", "America/North_Dakota/Center", "America/Rainy_River", "America/Rankin_Inlet", "America/Regina", "America/Swift_Current", "America/Tegucigalpa", "America/Winnipeg", "CST", "CST6CDT", "Canada/Central", "Canada/East-Saskatchewan", "Canada/Saskatchewan", "Chile/EasterIsland", "Etc/GMT+6", "Mexico/General", "Pacific/Easter", "Pacific/Galapagos", "SystemV/CST6", "SystemV/CST6CDT", "US/Central", "America/Bogota", "America/Cayman", "America/Detroit", "America/Eirunepe", "America/Fort_Wayne", "America/Grand_Turk", "America/Guayaquil", "America/Havana", "America/Indiana/Indianapolis", "America/Indiana/Knox", "America/Indiana/Marengo", "America/Indiana/Vevay", "America/Indianapolis", "America/Iqaluit", "America/Jamaica", "America/Kentucky/Louisville", "America/Kentucky/Monticello", "America/Knox_IN", "America/Lima", "America/Louisville", "America/Montreal", "America/Nassau", "America/New_York", "America/Nipigon", "America/Panama", "America/Pangnirtung", "America/Port-au-Prince", "America/Porto_Acre", "America/Rio_Branco", "America/Thunder_Bay", "America/Toronto", "Brazil/Acre", "Canada/Eastern", "Cuba", "EST", "EST5EDT", "Etc/GMT+5", "IET", "Jamaica", "SystemV/EST5", "SystemV/EST5EDT", "US/East-Indiana", "US/Eastern", "US/Indiana-Starke", "US/Michigan", "America/Anguilla", "America/Antigua", "America/Aruba", "America/Asuncion", "America/Barbados", "America/Boa_Vista", "America/Campo_Grande", "America/Caracas", "America/Cuiaba", "America/Curacao", "America/Dominica", "America/Glace_Bay", "America/Goose_Bay", "America/Grenada", "America/Guadeloupe", "America/Guyana", "America/Halifax", "America/La_Paz", "America/Manaus", "America/Martinique", "America/Montserrat", "America/Port_of_Spain", "America/Porto_Velho", "America/Puerto_Rico", "America/Santiago", "America/Santo_Domingo", "America/St_Kitts", "America/St_Lucia", "America/St_Thomas", "America/St_Vincent", "America/Thule", "America/Tortola", "America/Virgin", "Antarctica/Palmer", "Atlantic/Bermuda", "Atlantic/Stanley", "Brazil/West", "Canada/Atlantic", "Chile/Continental", "Etc/GMT+4", "PRT", "SystemV/AST4", "SystemV/AST4ADT", "America/St_Johns", "CNT", "Canada/Newfoundland", "AGT", "America/Araguaina", "America/Bahia", "America/Belem", "America/Buenos_Aires", "America/Catamarca", "America/Cayenne", "America/Cordoba", "America/Fortaleza", "America/Godthab", "America/Jujuy", "America/Maceio", "America/Mendoza", "America/Miquelon", "America/Montevideo", "America/Paramaribo", "America/Recife", "America/Rosario", "America/Sao_Paulo", "Antarctica/Rothera", "BET", "Brazil/East", "Etc/GMT+3", "America/Noronha", "Atlantic/South_Georgia", "Brazil/DeNoronha", "Etc/GMT+2", "America/Scoresbysund", "Atlantic/Azores", "Atlantic/Cape_Verde", "Etc/GMT+1", "Africa/Abidjan", "Africa/Accra", "Africa/Bamako", "Africa/Banjul", "Africa/Bissau", "Africa/Casablanca", "Africa/Conakry", "Africa/Dakar", "Africa/El_Aaiun", "Africa/Freetown", "Africa/Lome", "Africa/Monrovia", "Africa/Nouakchott", "Africa/Ouagadougou", "Africa/Sao_Tome", "Africa/Timbuktu", "America/Danmarkshavn", "Atlantic/Canary", "Atlantic/Faeroe", "Atlantic/Madeira", "Atlantic/Reykjavik", "Atlantic/St_Helena", "Eire", "Etc/GMT", "Etc/GMT+0", "Etc/GMT-0", "Etc/GMT0", "Etc/Greenwich", "Etc/UCT", "Etc/UTC", "Etc/Universal", "Etc/Zulu", "Europe/Belfast", "Europe/Dublin", "Europe/Lisbon", "Europe/London", "GB", "GB-Eire", "GMT", "GMT0", "Greenwich", "Iceland", "Portugal", "UCT", "UTC", "Universal", "WET", "Zulu", "Africa/Algiers", "Africa/Bangui", "Africa/Brazzaville", "Africa/Ceuta", "Africa/Douala", "Africa/Kinshasa", "Africa/Lagos", "Africa/Libreville", "Africa/Luanda", "Africa/Malabo", "Africa/Ndjamena", "Africa/Niamey", "Africa/Porto-Novo", "Africa/Tunis", "Africa/Windhoek", "Arctic/Longyearbyen", "Atlantic/Jan_Mayen", "CET", "ECT", "Etc/GMT-1", "Europe/Amsterdam", "Europe/Andorra", "Europe/Belgrade", "Europe/Berlin", "Europe/Bratislava", "Europe/Brussels", "Europe/Budapest", "Europe/Copenhagen", "Europe/Gibraltar", "Europe/Ljubljana", "Europe/Luxembourg", "Europe/Madrid", "Europe/Malta", "Europe/Monaco", "Europe/Oslo", "Europe/Paris", "Europe/Prague", "Europe/Rome", "Europe/San_Marino", "Europe/Sarajevo", "Europe/Skopje", "Europe/Stockholm", "Europe/Tirane", "Europe/Vaduz", "Europe/Vatican", "Europe/Vienna", "Europe/Warsaw", "Europe/Zagreb", "Europe/Zurich", "MET", "Poland", "ART", "Africa/Blantyre", "Africa/Bujumbura", "Africa/Cairo", "Africa/Gaborone", "Africa/Harare", "Africa/Johannesburg", "Africa/Kigali", "Africa/Lubumbashi", "Africa/Lusaka", "Africa/Maputo", "Africa/Maseru", "Africa/Mbabane", "Africa/Tripoli", "Asia/Amman", "Asia/Beirut", "Asia/Damascus", "Asia/Gaza", "Asia/Istanbul", "Asia/Jerusalem", "Asia/Nicosia", "Asia/Tel_Aviv", "CAT", "EET", "Egypt", "Etc/GMT-2", "Europe/Athens", "Europe/Bucharest", "Europe/Chisinau", "Europe/Helsinki", "Europe/Istanbul", "Europe/Kaliningrad", "Europe/Kiev", "Europe/Minsk", "Europe/Nicosia", "Europe/Riga", "Europe/Simferopol", "Europe/Sofia", "Europe/Tallinn", "Europe/Tiraspol", "Europe/Uzhgorod", "Europe/Vilnius", "Europe/Zaporozhye", "Israel", "Libya", "Turkey", "Africa/Addis_Ababa", "Africa/Asmera", "Africa/Dar_es_Salaam", "Africa/Djibouti", "Africa/Kampala", "Africa/Khartoum", "Africa/Mogadishu", "Africa/Nairobi", "Antarctica/Syowa", "Asia/Aden", "Asia/Baghdad", "Asia/Bahrain", "Asia/Kuwait", "Asia/Qatar", "Asia/Riyadh", "EAT", "Etc/GMT-3", "Europe/Moscow", "Indian/Antananarivo", "Indian/Comoro", "Indian/Mayotte", "W-SU", "Asia/Riyadh87", "Asia/Riyadh88", "Asia/Riyadh89", "Mideast/Riyadh87", "Mideast/Riyadh88", "Mideast/Riyadh89", "Asia/Tehran", "Iran", "Asia/Aqtau", "Asia/Baku", "Asia/Dubai", "Asia/Muscat", "Asia/Oral", "Asia/Tbilisi", "Asia/Yerevan", "Etc/GMT-4", "Europe/Samara", "Indian/Mahe", "Indian/Mauritius", "Indian/Reunion", "NET", "Asia/Kabul", "Asia/Aqtobe", "Asia/Ashgabat", "Asia/Ashkhabad", "Asia/Bishkek", "Asia/Dushanbe", "Asia/Karachi", "Asia/Samarkand", "Asia/Tashkent", "Asia/Yekaterinburg", "Etc/GMT-5", "Indian/Kerguelen", "Indian/Maldives", "PLT", "Asia/Calcutta", "IST", "Asia/Katmandu", "Antarctica/Mawson", "Antarctica/Vostok", "Asia/Almaty", "Asia/Colombo", "Asia/Dacca", "Asia/Dhaka", "Asia/Novosibirsk", "Asia/Omsk", "Asia/Qyzylorda", "Asia/Thimbu", "Asia/Thimphu", "BST", "Etc/GMT-6", "Indian/Chagos", "Asia/Rangoon", "Indian/Cocos", "Antarctica/Davis", "Asia/Bangkok", "Asia/Hovd", "Asia/Jakarta", "Asia/Krasnoyarsk", "Asia/Phnom_Penh", "Asia/Pontianak", "Asia/Saigon", "Asia/Vientiane", "Etc/GMT-7", "Indian/Christmas", "VST", "Antarctica/Casey", "Asia/Brunei", "Asia/Chongqing", "Asia/Chungking", "Asia/Harbin", "Asia/Hong_Kong", "Asia/Irkutsk", "Asia/Kashgar", "Asia/Kuala_Lumpur", "Asia/Kuching", "Asia/Macao", "Asia/Macau", "Asia/Makassar", "Asia/Manila", "Asia/Shanghai", "Asia/Singapore", "Asia/Taipei", "Asia/Ujung_Pandang", "Asia/Ulaanbaatar", "Asia/Ulan_Bator", "Asia/Urumqi", "Australia/Perth", "Australia/West", "CTT", "Etc/GMT-8", "Hongkong", "PRC", "Singapore", "Asia/Choibalsan", "Asia/Dili", "Asia/Jayapura", "Asia/Pyongyang", "Asia/Seoul", "Asia/Tokyo", "Asia/Yakutsk", "Etc/GMT-9", "JST", "Japan", "Pacific/Palau", "ROK", "ACT", "Australia/Adelaide", "Australia/Broken_Hill", "Australia/Darwin", "Australia/North", "Australia/South", "Australia/Yancowinna", "AET", "Antarctica/DumontDUrville", "Asia/Sakhalin", "Asia/Vladivostok", "Australia/ACT", "Australia/Brisbane", "Australia/Canberra", "Australia/Hobart", "Australia/Lindeman", "Australia/Melbourne", "Australia/NSW", "Australia/Queensland", "Australia/Sydney", "Australia/Tasmania", "Australia/Victoria", "Etc/GMT-10", "Pacific/Guam", "Pacific/Port_Moresby", "Pacific/Saipan", "Pacific/Truk", "Pacific/Yap", "Australia/LHI", "Australia/Lord_Howe", "Asia/Magadan", "Etc/GMT-11", "Pacific/Efate", "Pacific/Guadalcanal", "Pacific/Kosrae", "Pacific/Noumea", "Pacific/Ponape", "SST", "Pacific/Norfolk", "Antarctica/McMurdo", "Antarctica/South_Pole", "Asia/Anadyr", "Asia/Kamchatka", "Etc/GMT-12", "Kwajalein", "NST", "NZ", "Pacific/Auckland", "Pacific/Fiji", "Pacific/Funafuti", "Pacific/Kwajalein", "Pacific/Majuro", "Pacific/Nauru", "Pacific/Tarawa", "Pacific/Wake", "Pacific/Wallis", "NZ-CHAT", "Pacific/Chatham", "Etc/GMT-13", "Pacific/Enderbury", "Pacific/Tongatapu", "Etc/GMT-14", "Pacific/Kiritimati",};
		//String[] TIME_ZONES = {"(GMT-12:00) Etc/GMT+12", "(GMT-11:00) Etc/GMT+11", "(GMT-11:00) Pacific/Midway", "(GMT-11:00) Pacific/Niue", "(GMT-11:00) Pacific/Pago_Pago", "(GMT-11:00) Pacific/Samoa", "(GMT-11:00) US/Samoa", "(GMT-10:00) America/Adak", "(GMT-10:00) America/Atka", "(GMT-10:00) Etc/GMT+10", "(GMT-10:00) HST", "(GMT-10:00) Pacific/Honolulu", "(GMT-10:00) Pacific/Johnston", "(GMT-10:00) Pacific/Rarotonga", "(GMT-10:00) Pacific/Tahiti", "(GMT-10:00) SystemV/HST10", "(GMT-10:00) US/Aleutian", "(GMT-10:00) US/Hawaii", "(GMT-9:30) Pacific/Marquesas", "(GMT-9:00) AST", "(GMT-9:00) America/Anchorage", "(GMT-9:00) America/Juneau", "(GMT-9:00) America/Nome", "(GMT-9:00) America/Sitka", "(GMT-9:00) America/Yakutat", "(GMT-9:00) Etc/GMT+9", "(GMT-9:00) Pacific/Gambier", "(GMT-9:00) SystemV/YST9", "(GMT-9:00) SystemV/YST9YDT", "(GMT-9:00) US/Alaska", "(GMT-8:00) America/Dawson", "(GMT-8:00) America/Ensenada", "(GMT-8:00) America/Los_Angeles", "(GMT-8:00) America/Metlakatla", "(GMT-8:00) America/Santa_Isabel", "(GMT-8:00) America/Tijuana", "(GMT-8:00) America/Vancouver", "(GMT-8:00) America/Whitehorse", "(GMT-8:00) Canada/Pacific", "(GMT-8:00) Canada/Yukon", "(GMT-8:00) Etc/GMT+8", "(GMT-8:00) Mexico/BajaNorte", "(GMT-8:00) PST", "(GMT-8:00) PST8PDT", "(GMT-8:00) Pacific/Pitcairn", "(GMT-8:00) SystemV/PST8", "(GMT-8:00) SystemV/PST8PDT", "(GMT-8:00) US/Pacific", "(GMT-8:00) US/Pacific-New", "(GMT-7:00) America/Boise", "(GMT-7:00) America/Cambridge_Bay", "(GMT-7:00) America/Chihuahua", "(GMT-7:00) America/Creston", "(GMT-7:00) America/Dawson_Creek", "(GMT-7:00) America/Denver", "(GMT-7:00) America/Edmonton", "(GMT-7:00) America/Hermosillo", "(GMT-7:00) America/Inuvik", "(GMT-7:00) America/Mazatlan", "(GMT-7:00) America/Ojinaga", "(GMT-7:00) America/Phoenix", "(GMT-7:00) America/Shiprock", "(GMT-7:00) America/Yellowknife", "(GMT-7:00) Canada/Mountain", "(GMT-7:00) Etc/GMT+7", "(GMT-7:00) MST", "(GMT-7:00) MST7MDT", "(GMT-7:00) Mexico/BajaSur", "(GMT-7:00) Navajo", "(GMT-7:00) PNT", "(GMT-7:00) SystemV/MST7", "(GMT-7:00) SystemV/MST7MDT", "(GMT-7:00) US/Arizona", "(GMT-7:00) US/Mountain", "(GMT-6:00) America/Bahia_Banderas", "(GMT-6:00) America/Belize", "(GMT-6:00) America/Cancun", "(GMT-6:00) America/Chicago", "(GMT-6:00) America/Costa_Rica", "(GMT-6:00) America/El_Salvador", "(GMT-6:00) America/Guatemala", "(GMT-6:00) America/Indiana/Knox", "(GMT-6:00) America/Indiana/Tell_City", "(GMT-6:00) America/Knox_IN", "(GMT-6:00) America/Managua", "(GMT-6:00) America/Matamoros", "(GMT-6:00) America/Menominee", "(GMT-6:00) America/Merida", "(GMT-6:00) America/Mexico_City", "(GMT-6:00) America/Monterrey", "(GMT-6:00) America/North_Dakota/Beulah", "(GMT-6:00) America/North_Dakota/Center", "(GMT-6:00) America/North_Dakota/New_Salem", "(GMT-6:00) America/Rainy_River", "(GMT-6:00) America/Rankin_Inlet", "(GMT-6:00) America/Regina", "(GMT-6:00) America/Resolute", "(GMT-6:00) America/Swift_Current", "(GMT-6:00) America/Tegucigalpa", "(GMT-6:00) America/Winnipeg", "(GMT-6:00) CST", "(GMT-6:00) CST6CDT", "(GMT-6:00) Canada/Central", "(GMT-6:00) Canada/East-Saskatchewan", "(GMT-6:00) Canada/Saskatchewan", "(GMT-6:00) Chile/EasterIsland", "(GMT-6:00) Etc/GMT+6", "(GMT-6:00) Mexico/General", "(GMT-6:00) Pacific/Easter", "(GMT-6:00) Pacific/Galapagos", "(GMT-6:00) SystemV/CST6", "(GMT-6:00) SystemV/CST6CDT", "(GMT-6:00) US/Central", "(GMT-6:00) US/Indiana-Starke", "(GMT-5:00) America/Atikokan", "(GMT-5:00) America/Bogota", "(GMT-5:00) America/Cayman", "(GMT-5:00) America/Coral_Harbour", "(GMT-5:00) America/Detroit", "(GMT-5:00) America/Eirunepe", "(GMT-5:00) America/Fort_Wayne", "(GMT-5:00) America/Grand_Turk", "(GMT-5:00) America/Guayaquil", "(GMT-5:00) America/Havana", "(GMT-5:00) America/Indiana/Indianapolis", "(GMT-5:00) America/Indiana/Marengo", "(GMT-5:00) America/Indiana/Petersburg", "(GMT-5:00) America/Indiana/Vevay", "(GMT-5:00) America/Indiana/Vincennes", "(GMT-5:00) America/Indiana/Winamac", "(GMT-5:00) America/Indianapolis", "(GMT-5:00) America/Iqaluit", "(GMT-5:00) America/Jamaica", "(GMT-5:00) America/Kentucky/Louisville", "(GMT-5:00) America/Kentucky/Monticello", "(GMT-5:00) America/Lima", "(GMT-5:00) America/Louisville", "(GMT-5:00) America/Montreal", "(GMT-5:00) America/Nassau", "(GMT-5:00) America/New_York", "(GMT-5:00) America/Nipigon", "(GMT-5:00) America/Panama", "(GMT-5:00) America/Pangnirtung", "(GMT-5:00) America/Port-au-Prince", "(GMT-5:00) America/Porto_Acre", "(GMT-5:00) America/Rio_Branco", "(GMT-5:00) America/Thunder_Bay", "(GMT-5:00) America/Toronto", "(GMT-5:00) Brazil/Acre", "(GMT-5:00) Canada/Eastern", "(GMT-5:00) Cuba", "(GMT-5:00) EST", "(GMT-5:00) EST5EDT", "(GMT-5:00) Etc/GMT+5", "(GMT-5:00) IET", "(GMT-5:00) Jamaica", "(GMT-5:00) SystemV/EST5", "(GMT-5:00) SystemV/EST5EDT", "(GMT-5:00) US/East-Indiana", "(GMT-5:00) US/Eastern", "(GMT-5:00) US/Michigan", "(GMT-4:30) America/Caracas", "(GMT-4:00) America/Anguilla", "(GMT-4:00) America/Antigua", "(GMT-4:00) America/Aruba", "(GMT-4:00) America/Asuncion", "(GMT-4:00) America/Barbados", "(GMT-4:00) America/Blanc-Sablon", "(GMT-4:00) America/Boa_Vista", "(GMT-4:00) America/Campo_Grande", "(GMT-4:00) America/Cuiaba", "(GMT-4:00) America/Curacao", "(GMT-4:00) America/Dominica", "(GMT-4:00) America/Glace_Bay", "(GMT-4:00) America/Goose_Bay", "(GMT-4:00) America/Grenada", "(GMT-4:00) America/Guadeloupe", "(GMT-4:00) America/Guyana", "(GMT-4:00) America/Halifax", "(GMT-4:00) America/Kralendijk", "(GMT-4:00) America/La_Paz", "(GMT-4:00) America/Lower_Princes", "(GMT-4:00) America/Manaus", "(GMT-4:00) America/Marigot", "(GMT-4:00) America/Martinique", "(GMT-4:00) America/Moncton", "(GMT-4:00) America/Montserrat", "(GMT-4:00) America/Port_of_Spain", "(GMT-4:00) America/Porto_Velho", "(GMT-4:00) America/Puerto_Rico", "(GMT-4:00) America/Santiago", "(GMT-4:00) America/Santo_Domingo", "(GMT-4:00) America/St_Barthelemy", "(GMT-4:00) America/St_Kitts", "(GMT-4:00) America/St_Lucia", "(GMT-4:00) America/St_Thomas", "(GMT-4:00) America/St_Vincent", "(GMT-4:00) America/Thule", "(GMT-4:00) America/Tortola", "(GMT-4:00) America/Virgin", "(GMT-4:00) Antarctica/Palmer", "(GMT-4:00) Atlantic/Bermuda", "(GMT-4:00) Brazil/West", "(GMT-4:00) Canada/Atlantic", "(GMT-4:00) Chile/Continental", "(GMT-4:00) Etc/GMT+4", "(GMT-4:00) PRT", "(GMT-4:00) SystemV/AST4", "(GMT-4:00) SystemV/AST4ADT", "(GMT-3:30) America/St_Johns", "(GMT-3:30) CNT", "(GMT-3:30) Canada/Newfoundland", "(GMT-3:00) AGT", "(GMT-3:00) America/Araguaina", "(GMT-3:00) America/Argentina/Buenos_Aires", "(GMT-3:00) America/Argentina/Catamarca", "(GMT-3:00) America/Argentina/ComodRivadavia", "(GMT-3:00) America/Argentina/Cordoba", "(GMT-3:00) America/Argentina/Jujuy", "(GMT-3:00) America/Argentina/La_Rioja", "(GMT-3:00) America/Argentina/Mendoza", "(GMT-3:00) America/Argentina/Rio_Gallegos", "(GMT-3:00) America/Argentina/Salta", "(GMT-3:00) America/Argentina/San_Juan", "(GMT-3:00) America/Argentina/San_Luis", "(GMT-3:00) America/Argentina/Tucuman", "(GMT-3:00) America/Argentina/Ushuaia", "(GMT-3:00) America/Bahia", "(GMT-3:00) America/Belem", "(GMT-3:00) America/Buenos_Aires", "(GMT-3:00) America/Catamarca", "(GMT-3:00) America/Cayenne", "(GMT-3:00) America/Cordoba", "(GMT-3:00) America/Fortaleza", "(GMT-3:00) America/Godthab", "(GMT-3:00) America/Jujuy", "(GMT-3:00) America/Maceio", "(GMT-3:00) America/Mendoza", "(GMT-3:00) America/Miquelon", "(GMT-3:00) America/Montevideo", "(GMT-3:00) America/Paramaribo", "(GMT-3:00) America/Recife", "(GMT-3:00) America/Rosario", "(GMT-3:00) America/Santarem", "(GMT-3:00) America/Sao_Paulo", "(GMT-3:00) Antarctica/Rothera", "(GMT-3:00) Atlantic/Stanley", "(GMT-3:00) BET", "(GMT-3:00) Brazil/East", "(GMT-3:00) Etc/GMT+3", "(GMT-2:00) America/Noronha", "(GMT-2:00) Atlantic/South_Georgia", "(GMT-2:00) Brazil/DeNoronha", "(GMT-2:00) Etc/GMT+2", "(GMT-1:00) America/Scoresbysund", "(GMT-1:00) Atlantic/Azores", "(GMT-1:00) Atlantic/Cape_Verde", "(GMT-1:00) Etc/GMT+1", "(GMT0:00) Africa/Abidjan", "(GMT0:00) Africa/Accra", "(GMT0:00) Africa/Bamako", "(GMT0:00) Africa/Banjul", "(GMT0:00) Africa/Bissau", "(GMT0:00) Africa/Casablanca", "(GMT0:00) Africa/Conakry", "(GMT0:00) Africa/Dakar", "(GMT0:00) Africa/El_Aaiun", "(GMT0:00) Africa/Freetown", "(GMT0:00) Africa/Lome", "(GMT0:00) Africa/Monrovia", "(GMT0:00) Africa/Nouakchott", "(GMT0:00) Africa/Ouagadougou", "(GMT0:00) Africa/Sao_Tome", "(GMT0:00) Africa/Timbuktu", "(GMT0:00) America/Danmarkshavn", "(GMT0:00) Antarctica/Troll", "(GMT0:00) Atlantic/Canary", "(GMT0:00) Atlantic/Faeroe", "(GMT0:00) Atlantic/Faroe", "(GMT0:00) Atlantic/Madeira", "(GMT0:00) Atlantic/Reykjavik", "(GMT0:00) Atlantic/St_Helena", "(GMT0:00) Eire", "(GMT0:00) Etc/GMT", "(GMT0:00) Etc/GMT+0", "(GMT0:00) Etc/GMT-0", "(GMT0:00) Etc/GMT0", "(GMT0:00) Etc/Greenwich", "(GMT0:00) Etc/UCT", "(GMT0:00) Etc/UTC", "(GMT0:00) Etc/Universal", "(GMT0:00) Etc/Zulu", "(GMT0:00) Europe/Belfast", "(GMT0:00) Europe/Dublin", "(GMT0:00) Europe/Guernsey", "(GMT0:00) Europe/Isle_of_Man", "(GMT0:00) Europe/Jersey", "(GMT0:00) Europe/Lisbon", "(GMT0:00) Europe/London", "(GMT0:00) GB", "(GMT0:00) GB-Eire", "(GMT0:00) GMT", "(GMT0:00) GMT0", "(GMT0:00) Greenwich", "(GMT0:00) Iceland", "(GMT0:00) Portugal", "(GMT0:00) UCT", "(GMT0:00) UTC", "(GMT0:00) Universal", "(GMT0:00) WET", "(GMT0:00) Zulu", "(GMT+1:00) Africa/Algiers", "(GMT+1:00) Africa/Bangui", "(GMT+1:00) Africa/Brazzaville", "(GMT+1:00) Africa/Ceuta", "(GMT+1:00) Africa/Douala", "(GMT+1:00) Africa/Kinshasa", "(GMT+1:00) Africa/Lagos", "(GMT+1:00) Africa/Libreville", "(GMT+1:00) Africa/Luanda", "(GMT+1:00) Africa/Malabo", "(GMT+1:00) Africa/Ndjamena", "(GMT+1:00) Africa/Niamey", "(GMT+1:00) Africa/Porto-Novo", "(GMT+1:00) Africa/Tunis", "(GMT+1:00) Africa/Windhoek", "(GMT+1:00) Arctic/Longyearbyen", "(GMT+1:00) Atlantic/Jan_Mayen", "(GMT+1:00) CET", "(GMT+1:00) ECT", "(GMT+1:00) Etc/GMT-1", "(GMT+1:00) Europe/Amsterdam", "(GMT+1:00) Europe/Andorra", "(GMT+1:00) Europe/Belgrade", "(GMT+1:00) Europe/Berlin", "(GMT+1:00) Europe/Bratislava", "(GMT+1:00) Europe/Brussels", "(GMT+1:00) Europe/Budapest", "(GMT+1:00) Europe/Busingen", "(GMT+1:00) Europe/Copenhagen", "(GMT+1:00) Europe/Gibraltar", "(GMT+1:00) Europe/Ljubljana", "(GMT+1:00) Europe/Luxembourg", "(GMT+1:00) Europe/Madrid", "(GMT+1:00) Europe/Malta", "(GMT+1:00) Europe/Monaco", "(GMT+1:00) Europe/Oslo", "(GMT+1:00) Europe/Paris", "(GMT+1:00) Europe/Podgorica", "(GMT+1:00) Europe/Prague", "(GMT+1:00) Europe/Rome", "(GMT+1:00) Europe/San_Marino", "(GMT+1:00) Europe/Sarajevo", "(GMT+1:00) Europe/Skopje", "(GMT+1:00) Europe/Stockholm", "(GMT+1:00) Europe/Tirane", "(GMT+1:00) Europe/Vaduz", "(GMT+1:00) Europe/Vatican", "(GMT+1:00) Europe/Vienna", "(GMT+1:00) Europe/Warsaw", "(GMT+1:00) Europe/Zagreb", "(GMT+1:00) Europe/Zurich", "(GMT+1:00) MET", "(GMT+1:00) Poland", "(GMT+2:00) ART", "(GMT+2:00) Africa/Blantyre", "(GMT+2:00) Africa/Bujumbura", "(GMT+2:00) Africa/Cairo", "(GMT+2:00) Africa/Gaborone", "(GMT+2:00) Africa/Harare", "(GMT+2:00) Africa/Johannesburg", "(GMT+2:00) Africa/Kigali", "(GMT+2:00) Africa/Lubumbashi", "(GMT+2:00) Africa/Lusaka", "(GMT+2:00) Africa/Maputo", "(GMT+2:00) Africa/Maseru", "(GMT+2:00) Africa/Mbabane", "(GMT+2:00) Africa/Tripoli", "(GMT+2:00) Asia/Amman", "(GMT+2:00) Asia/Beirut", "(GMT+2:00) Asia/Damascus", "(GMT+2:00) Asia/Gaza", "(GMT+2:00) Asia/Hebron", "(GMT+2:00) Asia/Istanbul", "(GMT+2:00) Asia/Jerusalem", "(GMT+2:00) Asia/Nicosia", "(GMT+2:00) Asia/Tel_Aviv", "(GMT+2:00) CAT", "(GMT+2:00) EET", "(GMT+2:00) Egypt", "(GMT+2:00) Etc/GMT-2", "(GMT+2:00) Europe/Athens", "(GMT+2:00) Europe/Bucharest", "(GMT+2:00) Europe/Chisinau", "(GMT+2:00) Europe/Helsinki", "(GMT+2:00) Europe/Istanbul", "(GMT+2:00) Europe/Kiev", "(GMT+2:00) Europe/Mariehamn", "(GMT+2:00) Europe/Nicosia", "(GMT+2:00) Europe/Riga", "(GMT+2:00) Europe/Sofia", "(GMT+2:00) Europe/Tallinn", "(GMT+2:00) Europe/Tiraspol", "(GMT+2:00) Europe/Uzhgorod", "(GMT+2:00) Europe/Vilnius", "(GMT+2:00) Europe/Zaporozhye", "(GMT+2:00) Israel", "(GMT+2:00) Libya", "(GMT+2:00) Turkey", "(GMT+3:00) Africa/Addis_Ababa", "(GMT+3:00) Africa/Asmara", "(GMT+3:00) Africa/Asmera", "(GMT+3:00) Africa/Dar_es_Salaam", "(GMT+3:00) Africa/Djibouti", "(GMT+3:00) Africa/Juba", "(GMT+3:00) Africa/Kampala", "(GMT+3:00) Africa/Khartoum", "(GMT+3:00) Africa/Mogadishu", "(GMT+3:00) Africa/Nairobi", "(GMT+3:00) Antarctica/Syowa", "(GMT+3:00) Asia/Aden", "(GMT+3:00) Asia/Baghdad", "(GMT+3:00) Asia/Bahrain", "(GMT+3:00) Asia/Kuwait", "(GMT+3:00) Asia/Qatar", "(GMT+3:00) Asia/Riyadh", "(GMT+3:00) EAT", "(GMT+3:00) Etc/GMT-3", "(GMT+3:00) Europe/Kaliningrad", "(GMT+3:00) Europe/Minsk", "(GMT+3:00) Indian/Antananarivo", "(GMT+3:00) Indian/Comoro", "(GMT+3:00) Indian/Mayotte", "(GMT+3:07) Asia/Riyadh87", "(GMT+3:07) Asia/Riyadh88", "(GMT+3:07) Asia/Riyadh89", "(GMT+3:07) Mideast/Riyadh87", "(GMT+3:07) Mideast/Riyadh88", "(GMT+3:07) Mideast/Riyadh89", "(GMT+3:30) Asia/Tehran", "(GMT+3:30) Iran", "(GMT+4:00) Asia/Baku", "(GMT+4:00) Asia/Dubai", "(GMT+4:00) Asia/Muscat", "(GMT+4:00) Asia/Tbilisi", "(GMT+4:00) Asia/Yerevan", "(GMT+4:00) Etc/GMT-4", "(GMT+4:00) Europe/Moscow", "(GMT+4:00) Europe/Samara", "(GMT+4:00) Europe/Simferopol", "(GMT+4:00) Europe/Volgograd", "(GMT+4:00) Indian/Mahe", "(GMT+4:00) Indian/Mauritius", "(GMT+4:00) Indian/Reunion", "(GMT+4:00) NET", "(GMT+4:00) W-SU", "(GMT+4:30) Asia/Kabul", "(GMT+5:00) Antarctica/Mawson", "(GMT+5:00) Asia/Aqtau", "(GMT+5:00) Asia/Aqtobe", "(GMT+5:00) Asia/Ashgabat", "(GMT+5:00) Asia/Ashkhabad", "(GMT+5:00) Asia/Dushanbe", "(GMT+5:00) Asia/Karachi", "(GMT+5:00) Asia/Oral", "(GMT+5:00) Asia/Samarkand", "(GMT+5:00) Asia/Tashkent", "(GMT+5:00) Etc/GMT-5", "(GMT+5:00) Indian/Kerguelen", "(GMT+5:00) Indian/Maldives", "(GMT+5:00) PLT", "(GMT+5:30) Asia/Calcutta", "(GMT+5:30) Asia/Colombo", "(GMT+5:30) Asia/Kolkata", "(GMT+5:30) IST", "(GMT+5:45) Asia/Kathmandu", "(GMT+5:45) Asia/Katmandu", "(GMT+6:00) Antarctica/Vostok", "(GMT+6:00) Asia/Almaty", "(GMT+6:00) Asia/Bishkek", "(GMT+6:00) Asia/Dacca", "(GMT+6:00) Asia/Dhaka", "(GMT+6:00) Asia/Qyzylorda", "(GMT+6:00) Asia/Thimbu", "(GMT+6:00) Asia/Thimphu", "(GMT+6:00) Asia/Yekaterinburg", "(GMT+6:00) BST", "(GMT+6:00) Etc/GMT-6", "(GMT+6:00) Indian/Chagos", "(GMT+6:30) Asia/Rangoon", "(GMT+6:30) Indian/Cocos", "(GMT+7:00) Antarctica/Davis", "(GMT+7:00) Asia/Bangkok", "(GMT+7:00) Asia/Ho_Chi_Minh", "(GMT+7:00) Asia/Hovd", "(GMT+7:00) Asia/Jakarta", "(GMT+7:00) Asia/Novokuznetsk", "(GMT+7:00) Asia/Novosibirsk", "(GMT+7:00) Asia/Omsk", "(GMT+7:00) Asia/Phnom_Penh", "(GMT+7:00) Asia/Pontianak", "(GMT+7:00) Asia/Saigon", "(GMT+7:00) Asia/Vientiane", "(GMT+7:00) Etc/GMT-7", "(GMT+7:00) Indian/Christmas", "(GMT+7:00) VST", "(GMT+8:00) Antarctica/Casey", "(GMT+8:00) Asia/Brunei", "(GMT+8:00) Asia/Choibalsan", "(GMT+8:00) Asia/Chongqing", "(GMT+8:00) Asia/Chungking", "(GMT+8:00) Asia/Harbin", "(GMT+8:00) Asia/Hong_Kong", "(GMT+8:00) Asia/Kashgar", "(GMT+8:00) Asia/Krasnoyarsk", "(GMT+8:00) Asia/Kuala_Lumpur", "(GMT+8:00) Asia/Kuching", "(GMT+8:00) Asia/Macao", "(GMT+8:00) Asia/Macau", "(GMT+8:00) Asia/Makassar", "(GMT+8:00) Asia/Manila", "(GMT+8:00) Asia/Shanghai", "(GMT+8:00) Asia/Singapore", "(GMT+8:00) Asia/Taipei", "(GMT+8:00) Asia/Ujung_Pandang", "(GMT+8:00) Asia/Ulaanbaatar", "(GMT+8:00) Asia/Ulan_Bator", "(GMT+8:00) Asia/Urumqi", "(GMT+8:00) Australia/Perth", "(GMT+8:00) Australia/West", "(GMT+8:00) CTT", "(GMT+8:00) Etc/GMT-8", "(GMT+8:00) Hongkong", "(GMT+8:00) PRC", "(GMT+8:00) Singapore", "(GMT+8:45) Australia/Eucla", "(GMT+9:00) Asia/Dili", "(GMT+9:00) Asia/Irkutsk", "(GMT+9:00) Asia/Jayapura", "(GMT+9:00) Asia/Pyongyang", "(GMT+9:00) Asia/Seoul", "(GMT+9:00) Asia/Tokyo", "(GMT+9:00) Etc/GMT-9", "(GMT+9:00) JST", "(GMT+9:00) Japan", "(GMT+9:00) Pacific/Palau", "(GMT+9:00) ROK", "(GMT+9:30) ACT", "(GMT+9:30) Australia/Adelaide", "(GMT+9:30) Australia/Broken_Hill", "(GMT+9:30) Australia/Darwin", "(GMT+9:30) Australia/North", "(GMT+9:30) Australia/South", "(GMT+9:30) Australia/Yancowinna", "(GMT+10:00) AET", "(GMT+10:00) Antarctica/DumontDUrville", "(GMT+10:00) Asia/Khandyga", "(GMT+10:00) Asia/Yakutsk", "(GMT+10:00) Australia/ACT", "(GMT+10:00) Australia/Brisbane", "(GMT+10:00) Australia/Canberra", "(GMT+10:00) Australia/Currie", "(GMT+10:00) Australia/Hobart", "(GMT+10:00) Australia/Lindeman", "(GMT+10:00) Australia/Melbourne", "(GMT+10:00) Australia/NSW", "(GMT+10:00) Australia/Queensland", "(GMT+10:00) Australia/Sydney", "(GMT+10:00) Australia/Tasmania", "(GMT+10:00) Australia/Victoria", "(GMT+10:00) Etc/GMT-10", "(GMT+10:00) Pacific/Chuuk", "(GMT+10:00) Pacific/Guam", "(GMT+10:00) Pacific/Port_Moresby", "(GMT+10:00) Pacific/Saipan", "(GMT+10:00) Pacific/Truk", "(GMT+10:00) Pacific/Yap", "(GMT+10:30) Australia/LHI", "(GMT+10:30) Australia/Lord_Howe", "(GMT+11:00) Antarctica/Macquarie", "(GMT+11:00) Asia/Sakhalin", "(GMT+11:00) Asia/Ust-Nera", "(GMT+11:00) Asia/Vladivostok", "(GMT+11:00) Etc/GMT-11", "(GMT+11:00) Pacific/Efate", "(GMT+11:00) Pacific/Guadalcanal", "(GMT+11:00) Pacific/Kosrae", "(GMT+11:00) Pacific/Noumea", "(GMT+11:00) Pacific/Pohnpei", "(GMT+11:00) Pacific/Ponape", "(GMT+11:00) SST", "(GMT+11:30) Pacific/Norfolk", "(GMT+12:00) Antarctica/McMurdo", "(GMT+12:00) Antarctica/South_Pole", "(GMT+12:00) Asia/Anadyr", "(GMT+12:00) Asia/Kamchatka", "(GMT+12:00) Asia/Magadan", "(GMT+12:00) Etc/GMT-12", "(GMT+12:00) Kwajalein", "(GMT+12:00) NST", "(GMT+12:00) NZ", "(GMT+12:00) Pacific/Auckland", "(GMT+12:00) Pacific/Fiji", "(GMT+12:00) Pacific/Funafuti", "(GMT+12:00) Pacific/Kwajalein", "(GMT+12:00) Pacific/Majuro", "(GMT+12:00) Pacific/Nauru", "(GMT+12:00) Pacific/Tarawa", "(GMT+12:00) Pacific/Wake", "(GMT+12:00) Pacific/Wallis", "(GMT+12:45) NZ-CHAT", "(GMT+12:45) Pacific/Chatham", "(GMT+13:00) Etc/GMT-13", "(GMT+13:00) MIT", "(GMT+13:00) Pacific/Apia", "(GMT+13:00) Pacific/Enderbury", "(GMT+13:00) Pacific/Fakaofo", "(GMT+13:00) Pacific/Tongatapu", "(GMT+14:00) Etc/GMT-14", "(GMT+14:00) Pacific/Kiritimati"};
		String[] TIME_ZONES = getTimeZones();
		JFrame frame;
		JPanel board, sboard1, sboard2, radioboard1, radioboard2;
		JComboBox timezone1, timezone2, hour1, hour2, minute1, minute2, airport1, airport2 ;
		JLabel day1, day2;
		JRadioButton port1, tz1, port2, tz2;
		ButtonGroup group1, group2;
		boolean actionlisteners_active;
		
		CSVHandler csv;
		
		public Ticker()
		{
			
		timezone1 = new JComboBox(TIME_ZONES);
		timezone2 = new JComboBox(TIME_ZONES);
		hour1 = new JComboBox(HOURS);
		hour2 = new JComboBox(HOURS);
		minute1 = new JComboBox(MINUTES);
		minute2 = new JComboBox(MINUTES);
		day1 = new JLabel("", SwingConstants.RIGHT);
		day2 = new JLabel("", SwingConstants.RIGHT);
		port1 = new JRadioButton("Airport");
		port2 = new JRadioButton("Airport");
		tz1 = new JRadioButton("Timezone");
		tz2 = new JRadioButton("Timezone");
		tz1.setSelected(true);
		tz2.setSelected(true);
		group1 = new ButtonGroup();
		group2 = new ButtonGroup();
		group1.add(port1);
		group2.add(port2);
		group1.add(tz1);
		group2.add(tz2);
		radioboard1 = new JPanel();
		radioboard1.add(port1);
		radioboard1.add(tz1);
		radioboard2 = new JPanel();
		radioboard2.add(port2);
		radioboard2.add(tz2);
		actionlisteners_active = true;		
		
		csv = new CSVHandler("Airport_Timezone.txt", "map.txt");
		airport1 = new JComboBox(csv.getColumn("port"));
		airport2 = new JComboBox(csv.getColumn("port"));
		
		
		}

        public static void main(String[] args)
        {
            Ticker tk=new Ticker();
            tk.showTicker();
        }

        public void showTicker()
        {
            //JFrame.setDefaultLookAndFeelDecorated(true);

            frame = new JFrame("Ticker-Liju");
            frame.setSize(450,200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//frame.setUndecorated(true);
            //.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("ticker_icon.png")));
			//try{UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}catch(Exception e){e.printStackTrace();}
			try{frame.setIconImage(ImageIO.read(new File("Ticker.png")));} catch(Exception e){}
			board = new JPanel();
			board.setLayout(new GridLayout(4,2,10,5));
			sboard1 = new JPanel();
			sboard1.setLayout(new GridLayout(1,2,1,0));
			sboard2 = new JPanel();
			sboard2.setLayout(new GridLayout(1,2,1,0));
			
			hour1.addActionListener(this);
			hour1.setActionCommand("hour1");
			minute1.addActionListener(this);
			minute1.setActionCommand("minute1");
			hour2.addActionListener(this);
			hour2.setActionCommand("hour2");
			minute2.addActionListener(this);
			minute2.setActionCommand("minute2");
			timezone1.addActionListener(this);
			timezone1.setActionCommand("timezone1");
			timezone2.addActionListener(this);
			timezone2.setActionCommand("timezone2");
			port1.addActionListener(this);
			port1.setActionCommand("port1");
			port2.addActionListener(this);
			port2.setActionCommand("port2");
			tz1.addActionListener(this);
			tz1.setActionCommand("tz1");
			tz2.addActionListener(this);
			tz2.setActionCommand("tz2");
			airport1.addActionListener(this);
			airport1.setActionCommand("airport1");
			airport2.addActionListener(this);
			airport2.setActionCommand("airport2");
			
			/* Initialize the default values for the input elements */
			timezone2.setSelectedItem(TimeZone.getDefault().getID());
			timezone1.setSelectedItem("UTC");
			
			sboard1.add(hour1);
			sboard1.add(minute1);
			sboard2.add(hour2);
			sboard2.add(minute2);
			
			board.add(radioboard1);
			board.add(radioboard2);
			board.add(timezone1);
			board.add(timezone2);
			board.add(sboard1);
			board.add(sboard2);
			board.add(day1);
			board.add(day2);
            

            frame.getContentPane().add(board,BorderLayout.NORTH);
			frame.getContentPane().setBackground(Color.white);
			board.setBackground(Color.white);
			sboard1.setBackground(Color.white);
			sboard2.setBackground(Color.white);
			timezone1.setBackground(Color.white);
			timezone2.setBackground(Color.white);
			hour1.setBackground(Color.white);
			minute1.setBackground(Color.white);
			hour2.setBackground(Color.white);
			minute2.setBackground(Color.white);
			radioboard1.setBackground(Color.white);
			radioboard2.setBackground(Color.white);
			port1.setBackground(Color.white);
			port2.setBackground(Color.white);
			tz1.setBackground(Color.white);
			tz2.setBackground(Color.white);
			airport1.setBackground(Color.white);
			airport2.setBackground(Color.white);
			
			for (Component component : hour1.getComponents())
			{
				if (component instanceof JButton) {
				hour1.remove(component);
				}
			}
			
			for (Component component : minute1.getComponents())
			{
				if (component instanceof JButton) {
				minute1.remove(component);
				}
			}
			
			for (Component component : hour2.getComponents())
			{
				if (component instanceof JButton) {
				hour2.remove(component);
				}
			}
			
			for (Component component : minute2.getComponents())
			{
				if (component instanceof JButton) {
				minute2.remove(component);
				}
			}
//frame.getContentPane().add(new JSeparator(), BorderLayout.CENTER);
            //JLabel in=new JLabel("<html> <br><br><font color=blue>Liju</font></html>");
//in.setFont(new Font("Papyrus",Font.PLAIN, 10));
            //frame.getContentPane().add(in, BorderLayout.SOUTH);
//frame.pack();
            centreWindow(frame);
            frame.setVisible(true);

        }

		public void actionPerformed(ActionEvent e)
		{
			/*////System.out.println("action fired");
			////System.out.println(actionlisteners_active);
			////System.out.println(e.getActionCommand());*/
			////////System.out.println(e.getActionCommand());
			if(actionlisteners_active)
			switch(e.getActionCommand())
			{
				case "hour1":
				case "timezone2":
				if(!(hour1.getSelectedItem()=="Hour"||minute1.getSelectedItem()=="Minute"))
				{
					////////System.out.println("inside hr1");
					Date dt = new Date();
					dt.setHours(Integer.parseInt(hour1.getSelectedItem().toString()));
					dt.setMinutes(Integer.parseInt(minute1.getSelectedItem().toString()));
					Date dto = new Date(dt.getTime());
					convertTimeZone(dt,TimeZone.getTimeZone(timezone1.getSelectedItem().toString()),TimeZone.getTimeZone(timezone2.getSelectedItem().toString()));
                    setTimes(dto, dt, hour2, minute2, day2, day1);
				}
				break;
				case "minute1":
				if(!(hour1.getSelectedItem()=="Hour"||minute1.getSelectedItem()=="Minute"))
				{
					////////System.out.println("inside mn1");
					Date dt = new Date();
					dt.setHours(Integer.parseInt(hour1.getSelectedItem().toString()));
					dt.setMinutes(Integer.parseInt(minute1.getSelectedItem().toString()));
					Date dto = new Date(dt.getTime());
					convertTimeZone(dt,TimeZone.getTimeZone(timezone1.getSelectedItem().toString()),TimeZone.getTimeZone(timezone2.getSelectedItem().toString()));
					setTimes(dto, dt, hour2, minute2, day2, day1);
				}
				break;
				case "hour2":
				case "timezone1":
				if(!(hour2.getSelectedItem()=="Hour"||minute2.getSelectedItem()=="Minute"))
				{
					////////System.out.println("inside hr2");
					Date dt = new Date();
					dt.setHours(Integer.parseInt(hour2.getSelectedItem().toString()));
					dt.setMinutes(Integer.parseInt(minute2.getSelectedItem().toString()));
					Date dto = new Date(dt.getTime());
					convertTimeZone(dt,TimeZone.getTimeZone(timezone2.getSelectedItem().toString()),TimeZone.getTimeZone(timezone1.getSelectedItem().toString()));
					setTimes(dto, dt, hour1, minute1, day1, day2);
                    
				}
				break;
				case "minute2":
				if(!(hour2.getSelectedItem()=="Hour"||minute2.getSelectedItem()=="Minute"))
				{
					////////System.out.println("inside mn2");
					Date dt = new Date();
					dt.setHours(Integer.parseInt(hour2.getSelectedItem().toString()));
					dt.setMinutes(Integer.parseInt(minute2.getSelectedItem().toString()));
					Date dto = new Date(dt.getTime());
					convertTimeZone(dt,TimeZone.getTimeZone(timezone2.getSelectedItem().toString()),TimeZone.getTimeZone(timezone1.getSelectedItem().toString()));
                    setTimes(dto, dt, hour1, minute1, day1, day2);
				}
				break;
				case "port1":
				{
					////System.out.println("checked");
					this.board.remove(2);
					this.board.add(airport1,2);
					frame.getContentPane().revalidate();
					frame.getContentPane().repaint();
				}
				break;
				case "port2":
				{
					////System.out.println("checked");
					this.board.remove(3);
					this.board.add(airport2,3);
					frame.getContentPane().revalidate();
					frame.getContentPane().repaint();
				}
				break;
				case "tz1":
				{
					////System.out.println("checked");
					this.board.remove(2);
					this.board.add(timezone1,2);
					frame.getContentPane().revalidate();
					frame.getContentPane().repaint();
				}
				break;
				case "tz2":
				{
					////System.out.println("checked");
					this.board.remove(3);
					this.board.add(timezone2,3);
					frame.getContentPane().revalidate();
					frame.getContentPane().repaint();
				}
				break;
				case "airport1":
				{
					this.timezone1.setSelectedItem(this.csv.getMatch("port",this.airport1.getSelectedItem().toString(),"timezone"));
					////System.out.println("match: "+this.csv.getMatch("port",this.airport1.getSelectedItem().toString(),"timezone"));
					////System.out.println(this.timezone1.getSelectedItem().toString());
					
				}
				break;
				case "airport2":
				{
					this.timezone2.setSelectedItem(this.csv.getMatch("port",this.airport2.getSelectedItem().toString(),"timezone"));
					////System.out.println("match: "+this.csv.getMatch("port",this.airport1.getSelectedItem().toString(),"timezone"));
					////System.out.println(this.timezone1.getSelectedItem().toString());
					
				}
				break;
				default:
			}
		}


        public static void centreWindow(Window frame) {
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
            int y = (int) ((dimension.getHeight()/3 - frame.getHeight()));
            frame.setLocation(x, y);
        }


        public static void convertTimeZone(Date date, TimeZone fromTimeZone, TimeZone toTimeZone) {


            long milliseconds = date.getTime();

            milliseconds += (fromTimeZone.getRawOffset() * -1);
            if (fromTimeZone.inDaylightTime(date)) {
                milliseconds += (fromTimeZone.getDSTSavings() * -1);
            }
            milliseconds += toTimeZone.getRawOffset();
            if (toTimeZone.inDaylightTime(date)) {
                milliseconds += toTimeZone.getDSTSavings();
            }
            date.setTime(milliseconds);

        }
		
			public String[] getTimeZones() {

		String[] ids = TimeZone.getAvailableIDs();
		String[] tzs = new String[ids.length];
		////////System.out.println(ids.length);
		int i=0;
		for (int j=0;j<ids.length;j++) {
			////////System.out.println(TimeZone.getTimeZone(ids[j]).getID());
			tzs[i] = TimeZone.getTimeZone(ids[j]).getID();
			i++;
		}

		////////System.out.println("\nTotal TimeZone ID " + ids.length);
		return tzs;

	}

	private static String getString(TimeZone tz) {

		long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
		long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset())
                                  - TimeUnit.HOURS.toMinutes(hours);
		// avoid -4:-30 issue
		minutes = Math.abs(minutes);

		String result = "";
		if (hours > 0) {
			result = String.format("(GMT+%d:%02d) %s", hours, minutes, tz.getID());
		} else {
			result = String.format("(GMT%d:%02d) %s", hours, minutes, tz.getID());
		}

		return result;

	}
	
	public void setTimes(Date old_date, Date new_date, JComboBox hour, JComboBox minute, JLabel day, JLabel day2)
	{
					//////System.out.println("old_date hour: "+old_date.getHours());
					//////System.out.println("old_date mins: "+old_date.getMinutes());
					//////System.out.println("old_date secs: "+old_date.getSeconds());
					//////System.out.println("new_date hour: "+new_date.getHours());
					//////System.out.println("new_date mins: "+new_date.getMinutes());
					//////System.out.println("new_date secs: "+new_date.getSeconds());
					
					//char sign = new_date.getTime() > old_date.getTime()? '+' : '-';
					char sign;
					long difference;
					{
						if( new_date.getTime()>old_date.getTime())
						{
							
							sign = '+';
							long hr_diff = (new_date.getTime()-old_date.getTime())/(1000*60*60);
							long to_last_hr = 24 - old_date.getHours();
							long left_hours = hr_diff - to_last_hr;
							if(hr_diff<=to_last_hr)
							{
								difference = 0;
							}
							else
							{
								long remaining_days = left_hours/24;
								long remaining_hours = left_hours%24;
								difference = remaining_hours>0?remaining_days+1:remaining_days;
							}
							
						}
						else
						if( old_date.getTime()>new_date.getTime())
						{
							//////System.out.println("new date is greated");
							sign = '-';
							long hr_diff = (old_date.getTime()-new_date.getTime())/(1000*60*60);
							long to_last_hr = old_date.getHours();
							long left_hours = hr_diff - to_last_hr;
							//////System.out.println("hr diff"+hr_diff);
							//////System.out.println("to last"+to_last_hr);
							//////System.out.println("left"+left_hours);
							if(hr_diff<=to_last_hr)
							{
								difference = 0;
							}
							else
							{
								long remaining_days = left_hours/24;
								long remaining_hours = left_hours%24;
								difference = remaining_hours>0?remaining_days+1:remaining_days;
							}
							//////System.out.println("diff"+difference);
							
						}
						else
						{
							sign = ' ';
							difference = 0;
						}
					}
					
					
									
					this.actionlisteners_active = false;
					hour.setSelectedItem(""+new_date.getHours()+"");
                    minute.setSelectedItem(""+new_date.getMinutes()+"");
					actionlisteners_active = true;
					
					day.setText(sign+""+difference+" Day  ");
					day2.setText("");
	}

}
