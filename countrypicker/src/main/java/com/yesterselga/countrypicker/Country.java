package com.yesterselga.countrypicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import com.yesterselga.countrypicker.R.drawable;

public class Country {
    public static final Country[] COUNTRIES;
    private String code;
    private String name;
    private String dialCode;
    private int flag = -1;
    private static List<Country> allCountriesList;

    public Country(String code, String name, String dialCode, int flag) {
        this.code = code;
        this.name = name;
        this.dialCode = dialCode;
        this.flag = flag;
    }

    public Country() {
    }

    public String getDialCode() {
        return this.dialCode;
    }

    public void setDialCode(String dialCode) {
        this.dialCode = dialCode;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
        if(TextUtils.isEmpty(this.name)) {
            this.name = (new Locale("", code)).getDisplayName();
        }

    }

    public String getName() {
        return this.name;
    }

    public int getFlag() {
        return this.flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void loadFlagByCode(Context context) {
        if(this.flag == -1) {
            try {
                this.flag = context.getResources().getIdentifier("flag_" + this.code.toLowerCase(Locale.ENGLISH), "drawable", context.getPackageName());
            } catch (Exception var3) {
                var3.printStackTrace();
                this.flag = -1;
            }

        }
    }

    public static List<Country> getAllCountries() {
        if(allCountriesList == null) {
            allCountriesList = Arrays.asList(COUNTRIES);
        }

        return allCountriesList;
    }

    public static Country getCountryByISO(String countryIsoCode) {
        countryIsoCode = countryIsoCode.toUpperCase();
        Country c = new Country();
        c.setCode(countryIsoCode);
        int i = Arrays.binarySearch(COUNTRIES, c, new Country.ISOCodeComparator());
        return i < 0?null:COUNTRIES[i];
    }

    public static Country getCountryByName(String countryName) {
        Country[] var1 = COUNTRIES;
        int var2 = var1.length;

        for (Country c : var1) {
            if (countryName.equals(c.getName())) {
                return c;
            }
        }

        return null;
    }

    public static Country getCountryByLocale(Locale locale) {
        String countryIsoCode = locale.getISO3Country().substring(0, 2).toLowerCase();
        return getCountryByISO(countryIsoCode);
    }

    @SuppressLint("WrongConstant")
    public static Country getCountryFromSIM(Context context) {
        @SuppressLint("WrongConstant") TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
        return telephonyManager.getSimState() != 1?getCountryByISO(telephonyManager.getSimCountryIso()):null;
    }

    static {
        COUNTRIES = new Country[]{new Country("AD", "Andorra", "+376", drawable.flag_ad), new Country("AE", "United Arab Emirates", "+971", drawable.flag_ae), new Country("AF", "Afghanistan", "+93", drawable.flag_af), new Country("AG", "Antigua and Barbuda", "+1", drawable.flag_ag), new Country("AI", "Anguilla", "+1", drawable.flag_ai), new Country("AL", "Albania", "+355", drawable.flag_al), new Country("AM", "Armenia", "+374", drawable.flag_am), new Country("AO", "Angola", "+244", drawable.flag_ao), new Country("AQ", "Antarctica", "+672", drawable.flag_aq), new Country("AR", "Argentina", "+54", drawable.flag_ar), new Country("AS", "AmericanSamoa", "+1", drawable.flag_as), new Country("AT", "Austria", "+43", drawable.flag_at), new Country("AU", "Australia", "+61", drawable.flag_au), new Country("AW", "Aruba", "+297", drawable.flag_aw), new Country("AX", "Åland Islands", "+358", drawable.flag_ax), new Country("AZ", "Azerbaijan", "+994", drawable.flag_az), new Country("BA", "Bosnia and Herzegovina", "+387", drawable.flag_ba), new Country("BB", "Barbados", "+1", drawable.flag_bb), new Country("BD", "Bangladesh", "+880", drawable.flag_bd), new Country("BE", "Belgium", "+32", drawable.flag_be), new Country("BF", "Burkina Faso", "+226", drawable.flag_bf), new Country("BG", "Bulgaria", "+359", drawable.flag_bg), new Country("BH", "Bahrain", "+973", drawable.flag_bh), new Country("BI", "Burundi", "+257", drawable.flag_bi), new Country("BJ", "Benin", "+229", drawable.flag_bj), new Country("BL", "Saint Barthélemy", "+590", drawable.flag_bl), new Country("BM", "Bermuda", "+1", drawable.flag_bm), new Country("BN", "Brunei Darussalam", "+673", drawable.flag_bn), new Country("BO", "Bolivia, Plurinational State of", "+591", drawable.flag_bo), new Country("BQ", "Bonaire", "+599", drawable.flag_bq), new Country("BR", "Brazil", "+55", drawable.flag_br), new Country("BS", "Bahamas", "+1", drawable.flag_bs), new Country("BT", "Bhutan", "+975", drawable.flag_bt), new Country("BV", "Bouvet Island", "+47", drawable.flag_bv), new Country("BW", "Botswana", "+267", drawable.flag_bw), new Country("BY", "Belarus", "+375", drawable.flag_by), new Country("BZ", "Belize", "+501", drawable.flag_bz), new Country("CA", "Canada", "+1", drawable.flag_ca), new Country("CC", "Cocos (Keeling) Islands", "+61", drawable.flag_cc), new Country("CD", "Congo, The Democratic Republic of the", "+243", drawable.flag_cd), new Country("CF", "Central African Republic", "+236", drawable.flag_cf), new Country("CG", "Congo", "+242", drawable.flag_cg), new Country("CH", "Switzerland", "+41", drawable.flag_ch), new Country("CI", "Ivory Coast", "+225", drawable.flag_ci), new Country("CK", "Cook Islands", "+682", drawable.flag_ck), new Country("CL", "Chile", "+56", drawable.flag_cl), new Country("CM", "Cameroon", "+237", drawable.flag_cm), new Country("CN", "China", "+86", drawable.flag_cn), new Country("CO", "Colombia", "+57", drawable.flag_co), new Country("CR", "Costa Rica", "+506", drawable.flag_cr), new Country("CU", "Cuba", "+53", drawable.flag_cu), new Country("CV", "Cape Verde", "+238", drawable.flag_cv), new Country("CW", "Curacao", "+599", drawable.flag_cw), new Country("CX", "Christmas Island", "+61", drawable.flag_cx), new Country("CY", "Cyprus", "+357", drawable.flag_cy), new Country("CZ", "Czech Republic", "+420", drawable.flag_cz), new Country("DE", "Germany", "+49", drawable.flag_de), new Country("DJ", "Djibouti", "+253", drawable.flag_dj), new Country("DK", "Denmark", "+45", drawable.flag_dk), new Country("DM", "Dominica", "+1", drawable.flag_dm), new Country("DO", "Dominican Republic", "+1", drawable.flag_do), new Country("DZ", "Algeria", "+213", drawable.flag_dz), new Country("EC", "Ecuador", "+593", drawable.flag_ec), new Country("EE", "Estonia", "+372", drawable.flag_ee), new Country("EG", "Egypt", "+20", drawable.flag_eg), new Country("EH", "Western Sahara", "+212", drawable.flag_eh), new Country("ER", "Eritrea", "+291", drawable.flag_er), new Country("ES", "Spain", "+34", drawable.flag_es), new Country("ET", "Ethiopia", "+251", drawable.flag_et), new Country("FI", "Finland", "+358", drawable.flag_fi), new Country("FJ", "Fiji", "+679", drawable.flag_fj), new Country("FK", "Falkland Islands (Malvinas)", "+500", drawable.flag_fk), new Country("FM", "Micronesia, Federated States of", "+691", drawable.flag_fm), new Country("FO", "Faroe Islands", "+298", drawable.flag_fo), new Country("FR", "France", "+33", drawable.flag_fr), new Country("GA", "Gabon", "+241", drawable.flag_ga), new Country("GB", "United Kingdom", "+44", drawable.flag_gb), new Country("GD", "Grenada", "+1", drawable.flag_gd), new Country("GE", "Georgia", "+995", drawable.flag_ge), new Country("GF", "French Guiana", "+594", drawable.flag_gf), new Country("GG", "Guernsey", "+44", drawable.flag_gg), new Country("GH", "Ghana", "+233", drawable.flag_gh), new Country("GI", "Gibraltar", "+350", drawable.flag_gi), new Country("GL", "Greenland", "+299", drawable.flag_gl), new Country("GM", "Gambia", "+220", drawable.flag_gm), new Country("GN", "Guinea", "+224", drawable.flag_gn), new Country("GP", "Guadeloupe", "+590", drawable.flag_gp), new Country("GQ", "Equatorial Guinea", "+240", drawable.flag_gq), new Country("GR", "Greece", "+30", drawable.flag_gr), new Country("GS", "South Georgia and the South Sandwich Islands", "+500", drawable.flag_gs), new Country("GT", "Guatemala", "+502", drawable.flag_gt), new Country("GU", "Guam", "+1", drawable.flag_gu), new Country("GW", "Guinea-Bissau", "+245", drawable.flag_gw), new Country("GY", "Guyana", "+595", drawable.flag_gy), new Country("HK", "Hong Kong", "+852", drawable.flag_hk), new Country("HM", "Heard Island and McDonald Islands", "", drawable.flag_hm), new Country("HN", "Honduras", "+504", drawable.flag_hn), new Country("HR", "Croatia", "+385", drawable.flag_hr), new Country("HT", "Haiti", "+509", drawable.flag_ht), new Country("HU", "Hungary", "+36", drawable.flag_hu), new Country("ID", "Indonesia", "+62", drawable.flag_id), new Country("IE", "Ireland", "+353", drawable.flag_ie), new Country("IL", "Israel", "+972", drawable.flag_il), new Country("IM", "Isle of Man", "+44", drawable.flag_im), new Country("IN", "India", "+91", drawable.flag_in), new Country("IO", "British Indian Ocean Territory", "+246", drawable.flag_io), new Country("IQ", "Iraq", "+964", drawable.flag_iq), new Country("IR", "Iran, Islamic Republic of", "+98", drawable.flag_ir), new Country("IS", "Iceland", "+354", drawable.flag_is), new Country("IT", "Italy", "+39", drawable.flag_it), new Country("JE", "Jersey", "+44", drawable.flag_je), new Country("JM", "Jamaica", "+1", drawable.flag_jm), new Country("JO", "Jordan", "+962", drawable.flag_jo), new Country("JP", "Japan", "+81", drawable.flag_jp), new Country("KE", "Kenya", "+254", drawable.flag_ke), new Country("KG", "Kyrgyzstan", "+996", drawable.flag_kg), new Country("KH", "Cambodia", "+855", drawable.flag_kh), new Country("KI", "Kiribati", "+686", drawable.flag_ki), new Country("KM", "Comoros", "+269", drawable.flag_km), new Country("KN", "Saint Kitts and Nevis", "+1", drawable.flag_kn), new Country("KP", "North Korea", "+850", drawable.flag_kp), new Country("KR", "South Korea", "+82", drawable.flag_kr), new Country("KW", "Kuwait", "+965", drawable.flag_kw), new Country("KY", "Cayman Islands", "+345", drawable.flag_ky), new Country("KZ", "Kazakhstan", "+7", drawable.flag_kz), new Country("LA", "Lao People's Democratic Republic", "+856", drawable.flag_la), new Country("LB", "Lebanon", "+961", drawable.flag_lb), new Country("LC", "Saint Lucia", "+1", drawable.flag_lc), new Country("LI", "Liechtenstein", "+423", drawable.flag_li), new Country("LK", "Sri Lanka", "+94", drawable.flag_lk), new Country("LR", "Liberia", "+231", drawable.flag_lr), new Country("LS", "Lesotho", "+266", drawable.flag_ls), new Country("LT", "Lithuania", "+370", drawable.flag_lt), new Country("LU", "Luxembourg", "+352", drawable.flag_lu), new Country("LV", "Latvia", "+371", drawable.flag_lv), new Country("LY", "Libyan Arab Jamahiriya", "+218", drawable.flag_ly), new Country("MA", "Morocco", "+212", drawable.flag_ma), new Country("MC", "Monaco", "+377", drawable.flag_mc), new Country("MD", "Moldova, Republic of", "+373", drawable.flag_md), new Country("ME", "Montenegro", "+382", drawable.flag_me), new Country("MF", "Saint Martin", "+590", drawable.flag_mf), new Country("MG", "Madagascar", "+261", drawable.flag_mg), new Country("MH", "Marshall Islands", "+692", drawable.flag_mh), new Country("MK", "Macedonia, The Former Yugoslav Republic of", "+389", drawable.flag_mk), new Country("ML", "Mali", "+223", drawable.flag_ml), new Country("MM", "Myanmar", "+95", drawable.flag_mm), new Country("MN", "Mongolia", "+976", drawable.flag_mn), new Country("MO", "Macao", "+853", drawable.flag_mo), new Country("MP", "Northern Mariana Islands", "+1", drawable.flag_mp), new Country("MQ", "Martinique", "+596", drawable.flag_mq), new Country("MR", "Mauritania", "+222", drawable.flag_mr), new Country("MS", "Montserrat", "+1", drawable.flag_ms), new Country("MT", "Malta", "+356", drawable.flag_mt), new Country("MU", "Mauritius", "+230", drawable.flag_mu), new Country("MV", "Maldives", "+960", drawable.flag_mv), new Country("MW", "Malawi", "+265", drawable.flag_mw), new Country("MX", "Mexico", "+52", drawable.flag_mx), new Country("MY", "Malaysia", "+60", drawable.flag_my), new Country("MZ", "Mozambique", "+258", drawable.flag_mz), new Country("NA", "Namibia", "+264", drawable.flag_na), new Country("NC", "New Caledonia", "+687", drawable.flag_nc), new Country("NE", "Niger", "+227", drawable.flag_ne), new Country("NF", "Norfolk Island", "+672", drawable.flag_nf), new Country("NG", "Nigeria", "+234", drawable.flag_ng), new Country("NI", "Nicaragua", "+505", drawable.flag_ni), new Country("NL", "Netherlands", "+31", drawable.flag_nl), new Country("NO", "Norway", "+47", drawable.flag_no), new Country("NP", "Nepal", "+977", drawable.flag_np), new Country("NR", "Nauru", "+674", drawable.flag_nr), new Country("NU", "Niue", "+683", drawable.flag_nu), new Country("NZ", "New Zealand", "+64", drawable.flag_nz), new Country("OM", "Oman", "+968", drawable.flag_om), new Country("PA", "Panama", "+507", drawable.flag_pa), new Country("PE", "Peru", "+51", drawable.flag_pe), new Country("PF", "French Polynesia", "+689", drawable.flag_pf), new Country("PG", "Papua New Guinea", "+675", drawable.flag_pg), new Country("PH", "Philippines", "+63", drawable.flag_ph), new Country("PK", "Pakistan", "+92", drawable.flag_pk), new Country("PL", "Poland", "+48", drawable.flag_pl), new Country("PM", "Saint Pierre and Miquelon", "+508", drawable.flag_pm), new Country("PN", "Pitcairn", "+872", drawable.flag_pn), new Country("PR", "Puerto Rico", "+1", drawable.flag_pr), new Country("PS", "Palestinian Territory, Occupied", "+970", drawable.flag_ps), new Country("PT", "Portugal", "+351", drawable.flag_pt), new Country("PW", "Palau", "+680", drawable.flag_pw), new Country("PY", "Paraguay", "+595", drawable.flag_py), new Country("QA", "Qatar", "+974", drawable.flag_qa), new Country("RE", "Réunion", "+262", drawable.flag_re), new Country("RO", "Romania", "+40", drawable.flag_ro), new Country("RS", "Serbia", "+381", drawable.flag_rs), new Country("RU", "Russia", "+7", drawable.flag_ru), new Country("RW", "Rwanda", "+250", drawable.flag_rw), new Country("SA", "Saudi Arabia", "+966", drawable.flag_sa), new Country("SB", "Solomon Islands", "+677", drawable.flag_sb), new Country("SC", "Seychelles", "+248", drawable.flag_sc), new Country("SD", "Sudan", "+249", drawable.flag_sd), new Country("SE", "Sweden", "+46", drawable.flag_se), new Country("SG", "Singapore", "+65", drawable.flag_sg), new Country("SH", "Saint Helena, Ascension and Tristan Da Cunha", "+290", drawable.flag_sh), new Country("SI", "Slovenia", "+386", drawable.flag_si), new Country("SJ", "Svalbard and Jan Mayen", "+47", drawable.flag_sj), new Country("SK", "Slovakia", "+421", drawable.flag_sk), new Country("SL", "Sierra Leone", "+232", drawable.flag_sl), new Country("SM", "San Marino", "+378", drawable.flag_sm), new Country("SN", "Senegal", "+221", drawable.flag_sn), new Country("SO", "Somalia", "+252", drawable.flag_so), new Country("SR", "Suriname", "+597", drawable.flag_sr), new Country("SS", "South Sudan", "+211", drawable.flag_ss), new Country("ST", "Sao Tome and Principe", "+239", drawable.flag_st), new Country("SV", "El Salvador", "+503", drawable.flag_sv), new Country("SX", "  Sint Maarten", "+1", drawable.flag_sx), new Country("SY", "Syrian Arab Republic", "+963", drawable.flag_sy), new Country("SZ", "Swaziland", "+268", drawable.flag_sz), new Country("TC", "Turks and Caicos Islands", "+1", drawable.flag_tc), new Country("TD", "Chad", "+235", drawable.flag_td), new Country("TF", "French Southern Territories", "+262", drawable.flag_tf), new Country("TG", "Togo", "+228", drawable.flag_tg), new Country("TH", "Thailand", "+66", drawable.flag_th), new Country("TJ", "Tajikistan", "+992", drawable.flag_tj), new Country("TK", "Tokelau", "+690", drawable.flag_tk), new Country("TL", "East Timor", "+670", drawable.flag_tl), new Country("TM", "Turkmenistan", "+993", drawable.flag_tm), new Country("TN", "Tunisia", "+216", drawable.flag_tn), new Country("TO", "Tonga", "+676", drawable.flag_to), new Country("TR", "Turkey", "+90", drawable.flag_tr), new Country("TT", "Trinidad and Tobago", "+1", drawable.flag_tt), new Country("TV", "Tuvalu", "+688", drawable.flag_tv), new Country("TW", "Taiwan", "+886", drawable.flag_tw), new Country("TZ", "Tanzania, United Republic of", "+255", drawable.flag_tz), new Country("UA", "Ukraine", "+380", drawable.flag_ua), new Country("UG", "Uganda", "+256", drawable.flag_ug), new Country("UM", "U.S. Minor Outlying Islands", "", drawable.flag_um), new Country("US", "United States", "+1", drawable.flag_us), new Country("UY", "Uruguay", "+598", drawable.flag_uy), new Country("UZ", "Uzbekistan", "+998", drawable.flag_uz), new Country("VA", "Holy See (Vatican City State)", "+379", drawable.flag_va), new Country("VC", "Saint Vincent and the Grenadines", "+1", drawable.flag_vc), new Country("VE", "Venezuela, Bolivarian Republic of", "+58", drawable.flag_ve), new Country("VG", "Virgin Islands, British", "+1", drawable.flag_vg), new Country("VI", "Virgin Islands, U.S.", "+1", drawable.flag_vi), new Country("VN", "Viet Nam", "+84", drawable.flag_vn), new Country("VU", "Vanuatu", "+678", drawable.flag_vu), new Country("WF", "Wallis and Futuna", "+681", drawable.flag_wf), new Country("WS", "Samoa", "+685", drawable.flag_ws), new Country("XK", "Kosovo", "+383", drawable.flag_xk), new Country("YE", "Yemen", "+967", drawable.flag_ye), new Country("YT", "Mayotte", "+262", drawable.flag_yt), new Country("ZA", "South Africa", "+27", drawable.flag_za), new Country("ZM", "Zambia", "+260", drawable.flag_zm), new Country("ZW", "Zimbabwe", "+263", drawable.flag_zw)};
    }

    public static class NameComparator implements Comparator<Country> {
        public NameComparator() {
        }

        public int compare(Country country, Country t1) {
            return country.name.compareTo(t1.name);
        }
    }

    public static class ISOCodeComparator implements Comparator<Country> {
        public ISOCodeComparator() {
        }

        public int compare(Country country, Country t1) {
            return country.code.compareTo(t1.code);
        }
    }
}

