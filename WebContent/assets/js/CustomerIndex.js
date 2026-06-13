
function handleLocationChange() {
    const selectedValue = document.getElementById('locationDropdown').value;
    const locationInput = document.getElementById('locationInput');
      const currentLang = document.getElementById("languageSelect").value; 

    console.log("Selected value:", selectedValue);  // Log selection

    if (selectedValue === "current") {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                const lat = position.coords.latitude;
                const lng = position.coords.longitude;

                // Log the lat/lng values
                console.log("Latitude:", lat);
                console.log("Longitude:", lng);

                document.getElementById('lat').value = lat;
                document.getElementById('lng').value = lng;

                fetch("https://nominatim.openstreetmap.org/reverse?format=json&lat=" + lat + "&lon=" + lng)
                    .then(response => response.json())
                    .then(data => {
                        const address = data.address;
                        const city = address.city || address.town || address.village || address.state || "Unknown";
                        locationInput.value = city;
                    })
                    .catch(error => {
                        console.error("Reverse Geocoding failed:", error);
                        locationInput.value = "Unknown Location";
                    });
            }, function (error) {
                alert("Unable to access GPS: " + error.message);
            });
        } else {
            alert("Geolocation is not supported by this browser.");
        }
    } else {
        if (currentLang === "ar" && translations.ar["loc-" + selectedValue.toLowerCase()]) {
      locationInput.value = translations.ar["loc-" + selectedValue.toLowerCase()];
    } else {
      locationInput.value = selectedValue;
    }

        document.getElementById('lat').value = "";
        document.getElementById('lng').value = "";
    }
}

const translations = {
  en: {
    "nav-home": "Home",
    "nav-about": "About Us",
    "nav-service": "Services",
    "nav-contact": "Contact Us",
    "nav-partner": "Be with us",
    "nav-mybook": "My Bookings",
    "nav-welcome":"Welcome ",
    "nav-signout":"Sign Out",
    "section-top-salons": "Beauty Salons Near You",
    "section-experts": "Experts",
    "locationInput": "📍Select or Use Current Location",
    "loc-default": "-- Select Location --",
    "cat-default": "--Categories--",
    "loc-ir": "IRITTY",
    "loc-kn": "KANNUR",
    "loc-mt": "MATTANNUR",
    "loc-py": "PAYYANNUR",
    "loc-th": "THALASSERY",
    "loc-ch": "CHAKKARAKAL",

    "cat-body": "BODY TREATMENTS",
    "cat-brows": "BROWS AND LASHES",
    "cat-hair-removal": "HAIR REMOVAL",
    "cat-hair-service": "HAIR SERVICE",
    "cat-makeup": "MAKEUP SERVICE",
    "cat-massage": "MASSAGE THERAPY",
    "cat-nail": "NAIL SERVICE",
    "cat-skin": "SKIN CAREة",
    "cat-tanning": "TANNING",
    "cat-wellness": "WELLNESS SERVICE",
    "search-btn": "Search Salons",
    "footer-left": "© All Rights Reserved by ISAM GLOBAL",
    "footer-pp": "Privacy Policy",
    "footer-tc": "Terms & Conditions",
    "footer-ck": "Cookies"
  },
  ar: {
    "nav-home": "الصفحة الرئيسية",
    "nav-about": "معلومات عنا",
    "nav-service": "خدمات",
    "nav-contact": "اتصل بنا",
    "nav-partner": "كن معنا",
    "nav-mybook": "حجوزاتي", 
    "nav-welcome":"مرحبا ",
    "nav-signout":"تسجيل الخروج",
    "section-top-salons": "صالونات التجميل بالقرب منك",
    "section-experts": "الخبراء",
    "locationInput": "📍 حدد أو استخدم الموقع الحالي",
    "search-btn": "ابحث عن الصالونات",
    "footer-left": "© جميع الحقوق محفوظة لدى ISAM GLOBAL",
    "footer-pp": "سياسة الخصوصية",
    "footer-tc": "الشروط والأحكام",
    "footer-ck": " ملفات تعريف الارتباط",

    "loc-default": "-- حدد الموقع --",
    "loc-current": "📍 استخدم موقعي الحالي",
    "loc-ir": "إيرتي",
    "loc-kn": "كانور",
    "loc-mt": "ماتانور",
    "loc-py": "بايانور",
    "loc-th": "ثالاسيري",
    "loc-ch": "تشاكاراكال",

    "cat-default": "-- الفئات --",
    "cat-body": "علاجات الجسم",
    "cat-brows": "الحواجب والرموش",
    "cat-hair-removal": "إزالة الشعر",
    "cat-hair-service": "خدمة الشعر",
    "cat-makeup": "خدمة المكياج",
    "cat-massage": "العلاج بالتدليك",
    "cat-nail": "خدمة الأظافر",
    "cat-skin": "العناية بالبشرة",
    "cat-tanning": "التسمير",
    "cat-wellness": "خدمة العافية",

   "section-top-salons": "صالونات التجميل بالقرب منك",
  "section-experts": "الخبراء",
  //"footer-text": "© 2025 بليكس صالون التجميل",
  "book-now": "احجز الآن",
  "label-location": "📍 الموقع: "
  }
};

function changeLanguage() {
  const lang = document.getElementById("languageSelect").value;
  const dict = translations[lang];

  for (const key in dict) {
    const element = document.getElementById(key);
    if (element) {
      if (element.tagName === "INPUT") {
        element.placeholder = dict[key];
      } else if (element.tagName === "BUTTON") {
        element.innerText = dict[key];
      } else {
        element.textContent = dict[key];
      }
    }
  }

  // Set text direction if Arabic
  document.documentElement.setAttribute("lang", lang);

  if (lang === "ar") {
    document.body.classList.add("rtl-mode");
  } else {
    document.body.classList.remove("rtl-mode");
  }
localStorage.setItem("preferredLang", lang);
 

    }
window.onload = function() {
  const savedLang = localStorage.getItem("preferredLang") || "en";
  document.getElementById("languageSelect").value = savedLang;
  changeLanguage();
};
document.querySelectorAll('.book-now').forEach(btn => {
  btn.textContent = translations[lang]["book-now"];
});

// Update location labels
document.querySelectorAll('.label-location').forEach(label => {
  label.textContent = translations[lang]["label-location"];
});

