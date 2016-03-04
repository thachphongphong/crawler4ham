package ham.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dtlinh on 3/2/2016.
 */
public class HAMTest {
    private  static String html = "<!DOCTYPE html>\n" +
            "<div class='row row--content' id='js-row--content'>\n" +
            "<div class='row row--secondary' id='js-secondary-nav'>\n" +
            "<div class='row__inner row__inner--secondary row__inner--full-width'>\n" +
            "<div class='split--left row__title--secondary'><div class='icon--place--pin--before js-place-title place-title'><div class='place-title__wrapper'>\n" +
            "<span class='icon--chevron-down nav__submenu__trigger--icon'></span>\n" +
            "<div class='nav__submenu place-title__submenu'>\n" +
            "<div class='nav--stacked nav__submenu__content js-submenu icon--tapered-arrow-up--after icon--white--after'>\n" +
            "<div class='nav__submenu__button'>\n" +
            "<a class='btn btn--linkblue btn--medium btn--full-width' href='/vietnam/central-vietnam/hoi-an/places'>\n" +
            "See All\n" +
            "</a>\n" +
            "</div>\n" +
            "</div>\n" +
            "</div><a class='js-place-title-nav place-title--text place-title__heading' data-slug='/vietnam/central-vietnam/hoi-an' href='/vietnam/central-vietnam/hoi-an'>Hoi An</a></div><span class='place-title__separator'>/</span><div class='place-title__wrapper'>\n" +
            "<span class='icon--chevron-down nav__submenu__trigger--icon'></span><div class='nav__submenu place-title__submenu'>\n" +
            "<div class='nav--stacked nav__submenu__content js-submenu icon--tapered-arrow-up--after icon--white--after'>\n" +
            "<div class='nav__submenu__button'>\n" +
            "<a class='btn btn--linkblue btn--medium btn--full-width' href='/vietnam/places'>\n" +
            "See All\n" +
            "</a>\n" +
            "</div>\n" +
            "</div>\n" +
            "</div><a class='js-place-title-nav place-title--text place-title__parent' data-slug='/vietnam' href='/vietnam'>Vietnam</a></div></div></div>\n" +
            "<script id='tmpl-nav-item' type='text/html'>\n" +
            "<a class='has-image js-nav-item nav__item nav__submenu__item place-title__submenu__item' href='/{{slug}}' itemprop='url'><img alt='{{title}}' class='nav__submenu__item--image' src='{{image_url}}'>\n" +
            "<div class=' nav__submenu__item__text' itemprop='name'>\n" +
            "{{title}}</div></a>\n" +
            "\n" +
            "</script>\n" +
            "<script id='tmpl-nav-item-no-image' type='text/html'>\n" +
            "<a class='js-nav-item nav__item nav__submenu__item place-title__submenu__item--no-image' href='/{{slug}}' itemprop='url'><div class='nav__icon icon--destination--pin--before  nav__submenu__item__text' itemprop='name'>\n" +
            "{{title}}</div></a>\n" +
            "\n" +
            "</script>\n" +
            "\n" +
            "\n" +
            "<div class='nav--secondary__container'>\n" +
            "<div class='nav--secondary nav__tabs wv--hidden-block'>\n" +
            "<div class='grid-wrapper--0'>\n" +
            "<div class='col--one-half'>\n" +
            "<div class='nav--secondary__col nav--secondary__title copy--h4'>\n" +
            "Things to do\n" +
            "</div>\n" +
            "</div>\n" +
            "<div class='col--one-half'>\n" +
            "<a class='nav--secondary__other-toggle nav--secondary__col js-toggle-active' data-toggle-me='true' data-toggle-target='.js-secondary-nav-other-menu'>\n" +
            "Other sections\n" +
            "<span class='nav--secondary__other-toggle--icon icon--chevron-down icon--body-grey'></span>\n" +
            "<span class='nav--secondary__other-toggle--icon icon--chevron-up icon--body-grey'></span>\n" +
            "</a>\n" +
            "</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "<nav aria-label='Category navigation' class='wv--split--right nav--secondary js-secondary-nav-other-menu nav--secondary--categories' itemscope='itemscope' itemtype='http://schema.org/SiteNavigationElement' role='navigation'>\n" +
            "<h6 class='accessibility'>Category navigation</h6>\n" +
            "<div class='split--right__inner wv--nav--inline'>\n" +
            "<a class='js-nav-item nav__item nav__item--secondary' href='/vietnam/central-vietnam/hoi-an' itemprop='url'><div class='wv--hidden--before nav__submenu__item__text' itemprop='name'>\n" +
            "Highlights</div></a>\n" +
            "\n" +
            "<div class='nav__item nav__submenu__trigger js-secondary-nav-submenu'>\n" +
            "<a aria-label='Current page' class='is-current js-nav-item nav__item nav__item--secondary' href='/vietnam/central-vietnam/hoi-an/things-to-do' itemprop='url'><div class='wv--hidden--before nav__submenu__item__text' itemprop='name'>\n" +
            "Things to do</div></a>\n" +
            "<div class='nav__submenu'>\n" +
            "<div class='nav--stacked nav__submenu__content js-submenu icon--tapered-arrow-up--after icon--white--after'>\n" +
            "<a class='js-nav-item nav__item nav__submenu__item' href='/vietnam/central-vietnam/hoi-an/activities' itemprop='url'><div class='nav__icon icon--activity--before  nav__submenu__item__text' itemprop='name'>\n" +
            "Activities</div></a>\n" +
            "\n" +
            "<a class='js-nav-item nav__item nav__submenu__item' href='/vietnam/central-vietnam/hoi-an/tours' itemprop='url'><div class='nav__icon icon--tour--before  nav__submenu__item__text' itemprop='name'>\n" +
            "Tours</div></a>\n" +
            "\n" +
            "<a class='js-nav-item nav__item nav__submenu__item' href='/vietnam/central-vietnam/hoi-an/sights' itemprop='url'><div class='nav__icon icon--sight--before  nav__submenu__item__text' itemprop='name'>\n" +
            "Sights</div></a>\n" +
            "\n" +
            "<a class='js-nav-item nav__item nav__submenu__item' href='/vietnam/central-vietnam/hoi-an/restaurants' itemprop='url'><div class='nav__icon icon--restaurant--before  nav__submenu__item__text' itemprop='name'>\n" +
            "Restaurants</div></a>\n" +
            "\n" +
            "<a class='js-nav-item nav__item nav__submenu__item' href='/vietnam/central-vietnam/hoi-an/shopping' itemprop='url'><div class='nav__icon icon--shopping--before  nav__submenu__item__text' itemprop='name'>\n" +
            "Shopping</div></a>\n" +
            "\n" +
            "<a class='js-nav-item nav__item nav__submenu__item' href='/vietnam/central-vietnam/hoi-an/transport' itemprop='url'><div class='nav__icon icon--transport--before  nav__submenu__item__text' itemprop='name'>\n" +
            "Transport</div></a>\n" +
            "\n" +
            "<a class='js-nav-item nav__item nav__submenu__item' href='/vietnam/central-vietnam/hoi-an/entertainment-nightlife' itemprop='url'><div class='nav__icon icon--entertainment--before  nav__submenu__item__text' itemprop='name'>\n" +
            "Entertainment</div></a>\n" +
            "\n" +
            "<a class='js-nav-item nav__item nav__submenu__item' href='/vietnam/central-vietnam/hoi-an/events' itemprop='url'><div class='nav__icon icon--event--before  nav__submenu__item__text' itemprop='name'>\n" +
            "Events</div></a>\n" +
            "\n" +
            "</div>\n" +
            "</div>\n" +
            "<div class='nav__submenu__trigger--responsive wv--hidden js-toggle-active' data-toggle-class='is-visible' data-toggle-me='true' data-toggle-target='.js-secondary-nav-submenu'>\n" +
            "<div class='nav__submenu__trigger--icon icon--chevron-down icon--body-grey'></div>\n" +
            "<div class='nav__submenu__trigger--icon icon--chevron-up icon--body-grey'></div>\n" +
            "</div>\n" +
            "\n" +
            "\n" +
            "</div>\n" +
            "<a class='js-nav-item nav__item nav__item--secondary' href='/vietnam/central-vietnam/hoi-an/hotels' itemprop='url'><div class='wv--hidden--before nav__submenu__item__text' itemprop='name'>\n" +
            "Hotels</div></a>\n" +
            "\n" +
            "<a class='js-nav-item nav__item nav__item--secondary' href='/vietnam/central-vietnam/hoi-an/essential-information' itemprop='url'><div class='wv--hidden--before nav__submenu__item__text' itemprop='name'>\n" +
            "Essential information</div></a>\n" +
            "\n" +
            "<a class='js-nav-item nav__item nav__item--secondary' href='/vietnam/central-vietnam/hoi-an/travel-tips-and-articles' itemprop='url'><div class='wv--hidden--before nav__submenu__item__text' itemprop='name'>\n" +
            "Tips &amp; articles</div></a>\n" +
            "\n" +
            "<a class='js-nav-item nav__item nav__item--secondary' href='/vietnam/central-vietnam/hoi-an/images' itemprop='url'><div class='wv--hidden--before nav__submenu__item__text' itemprop='name'>\n" +
            "Image gallery</div></a>\n" +
            "\n" +
            "\n" +
            "</div>\n" +
            "</nav>\n" +
            "</div>\n" +
            "\n" +
            "\n" +
            "</div>\n" +
            "</div>\n" +
            "\n" +
            "<div class='row__inner row__inner--content row__inner--full-width'>\n" +
            "<div class='grid-wrapper--10 grid--main-layout'>\n" +
            "<div class='nav--left wv--table' id='js-left-nav'>\n" +
            "<div id='js-stack-list-aside'>\n" +
            "<div class='card nav--left__group'>\n" +
            "<a class='nav--left__item js-all-item' data-item-kind='stack:all' href='/vietnam/central-vietnam/hoi-an/things-to-do'>\n" +
            "All things to do in Hoi An\n" +
            "<span class='nav--left__item__facet'>(343)</span>\n" +
            "</a>\n" +
            "\n" +
            "</div>\n" +
            "<div class='card nav--left__group nav--left__group--linked'>\n" +
            "<label class='nav--left__title copy--h5' for='ac-'>\n" +
            "Collections\n" +
            "</label>\n" +
            "<a class='nav--left__item js--item icon--chevron-right--after' data-item-kind='stack:' href='/vietnam/central-vietnam/hoi-an/things-to-do/top-things-to-do-in-hoi-an'>\n" +
            "Top things to do in Hoi An\n" +
            "</a>\n" +
            "\n" +
            "</div>\n" +
            "<div class='card nav--left__group nav--left__group--linked'>\n" +
            "<label class='nav--left__title copy--h5' for='ac-'>\n" +
            "Types\n" +
            "</label>\n" +
            "<a class='nav--left__item js--item nav--left__item--icon icon--activity--before icon--chevron-right--after' data-item-kind='stack:' href='/vietnam/central-vietnam/hoi-an/activities'>\n" +
            "Activities\n" +
            "<span class='nav--left__item__facet'>(163)</span>\n" +
            "</a>\n" +
            "\n" +
            "<a class='nav--left__item js--item nav--left__item--icon icon--tour--before icon--chevron-right--after' data-item-kind='stack:' href='/vietnam/central-vietnam/hoi-an/tours'>\n" +
            "Tours\n" +
            "<span class='nav--left__item__facet'>(37)</span>\n" +
            "</a>\n" +
            "\n" +
            "<a class='nav--left__item js--item is-active nav--left__item--icon icon--sight--before icon--chevron-right--after' data-item-kind='stack:' href='/vietnam/central-vietnam/hoi-an/sights'>\n" +
            "Sights\n" +
            "<span class='nav--left__item__facet'>(32)</span>\n" +
            "</a>\n" +
            "\n" +
            "<a class='nav--left__item js--item nav--left__item--icon icon--restaurant--before icon--chevron-right--after' data-item-kind='stack:' href='/vietnam/central-vietnam/hoi-an/restaurants'>\n" +
            "Restaurants\n" +
            "<span class='nav--left__item__facet'>(29)</span>\n" +
            "</a>\n" +
            "\n" +
            "<a class='nav--left__item js--item nav--left__item--icon icon--shopping--before icon--chevron-right--after' data-item-kind='stack:' href='/vietnam/central-vietnam/hoi-an/shopping'>\n" +
            "Shopping\n" +
            "<span class='nav--left__item__facet'>(18)</span>\n" +
            "</a>\n" +
            "\n" +
            "<a class='nav--left__item js--item nav--left__item--icon icon--transport--before icon--chevron-right--after' data-item-kind='stack:' href='/vietnam/central-vietnam/hoi-an/transport'>\n" +
            "Transport\n" +
            "<span class='nav--left__item__facet'>(12)</span>\n" +
            "</a>\n" +
            "\n" +
            "<a class='nav--left__item js--item nav--left__item--icon icon--entertainment--before icon--chevron-right--after' data-item-kind='stack:' href='/vietnam/central-vietnam/hoi-an/entertainment-nightlife'>\n" +
            "Entertainment\n" +
            "<span class='nav--left__item__facet'>(11)</span>\n" +
            "</a>\n" +
            "\n" +
            "<a class='nav--left__item js--item nav--left__item--icon icon--event--before icon--chevron-right--after' data-item-kind='stack:' href='/vietnam/central-vietnam/hoi-an/events'>\n" +
            "Events\n" +
            "<span class='nav--left__item__facet'>(1)</span>\n" +
            "</a>\n" +
            "\n" +
            "</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "\n" +
            "<div class='stack js-stack'>\n" +
            "<div class='card card--page ttd' role='main'>\n" +
            "<div class='card--page__header card--page__header--headline ttd__header'>\n" +
            "<h1 class='copy--h1'>\n" +
            "Chinese All-Community Assembly Hall\n" +
            "</h1>\n" +
            "<div class='card--page__breadcrumb icon--sight--pin--simple--before'>\n" +
            "sights / \n" +
            "<span class='card--page__breadcrumb--type'>\n" +
            "<a class='card--page__breadcrumb__link' href='/vietnam/central-vietnam/hoi-an/sights/architecture'>Architecture</a>\n" +
            "</span>\n" +
            "</div>\n" +
            "</div>\n" +
            "<div class='card--page__content'>\n" +
            "<div class='grid-wrapper--20'>\n" +
            "<div class='col--one-whole wv--col--three-fifths no-print js-will-expand'>\n" +
            "<div class='card--page__fill media-gallery js-media-gallery'>\n" +
            "<div class='js-container js-tabs-content map-only media-gallery__container'>\n" +
            "<button class='media-gallery__resizer js-media-gallery-resizer js-toggle-active wv--inline-block' data-toggle-class='is-expanded' data-toggle-target='.js-will-expand' type='button'>\n" +
            "<span class='accessibility'>Resize image gallery and map</span>\n" +
            "<span class='icon--white icon--expand media-gallery__icon'></span>\n" +
            "</button>\n" +
            "<div class='tab__content' id='js-tab-map'>\n" +
            "<div class='poi-map poi-map--dynamic js-poi-map is-closed'>\n" +
            "<a class='poi-map__toggle js-poi-map-placeholder' href='https://www.google.com/maps/place/15.87742,108.32982/' rel='map'>\n" +
            "<img alt='' class='poi-map__img' height='360' src='https://maps.googleapis.com/maps/api/staticmap?key=AIzaSyBQxopw4OR08VaLVtHaY4XEXWk3dvLSj5k&amp;size=640x360&amp;zoom=15&amp;scale=2&amp;markers=scale:|icon:http%3A%2F%2Fassets.staticlp.com%2Fassets%2Fshared%2Fpoi-map-marker.png|15.87742,108.32982&amp;style=feature:water|element:geometry|color:0xcbdae7&amp;style=feature:landscape.man_made|element:geometry.fill|color:0xeff1f3&amp;style=feature:road|element:labels.text.stroke|color:0xffffff&amp;style=feature:road.arterial|element:geometry.fill|color:0xffffff&amp;style=feature:road.arterial|element:geometry.stroke|visibility:off&amp;style=feature:road.highway|element:geometry.fill|color:0x16c98d&amp;style=feature:road.highway|element:geometry.stroke|visibility:off&amp;style=feature:road.local|element:geometry.stroke|visibility:off&amp;style=feature:road.local|element:labels|visibility:off&amp;style=feature:poi.park|element:geometry.fill|color:0xc8e6aa&amp;style=feature:poi.school|element:geometry|color:0xdfdad3&amp;style=feature:poi.medical|element:geometry|color:0xfa5e5b' width='640'>\n" +
            "\n" +
            "</a>\n" +
            "<div class='poi-map__container mv--inline js-poi-map-container' data-icon='http://assets.staticlp.com/assets/shared/poi-map-marker.png' data-latitude='15.87742' data-longitude='108.32982' data-marker data-scale='2' data-zoom='15' style='padding-top: 56.25%'></div>\n" +
            "\n" +
            "<div class='nearby-pois js-poi-list'>\n" +
            "<ul class='nearby-pois__list'>\n" +
            "<li class='icon--sight--pin--before js-poi nearby-pois__poi' data-latitude='15.87732174' data-longitude='108.3293941' data-topic='sight'>\n" +
            "<a class='nearby-pois__title copy--h4' href='/vietnam/central-vietnam/hoi-an/sights/museums-galleries/museum-trading-ceramics'>\n" +
            "Museum of Trading Ceramics\n" +
            "</a>\n" +
            "<p class='nearby-pois__description'>\n" +
            "\n" +
            "Occupies a restored wooden house and contains artefacts from all over Asia, with oddities from as far afield as Egypt. While this...\n" +
            "</p>\n" +
            "</li>\n" +
            "<li class='icon--sight--pin--before js-poi nearby-pois__poi' data-latitude='15.87769' data-longitude='108.3302077' data-topic='sight'>\n" +
            "<a class='nearby-pois__title copy--h4' href='/vietnam/central-vietnam/hoi-an/sights/religious/assembly-hall-fujian-chinese-congregation'>\n" +
            "Assembly Hall of the Fujian Chinese Congregation\n" +
            "</a>\n" +
            "<p class='nearby-pois__description'>\n" +
            "\n" +
            "Originally a traditional assembly hall, this structure was later transformed into a temple for the worship of Thien Hau, a deity from...\n" +
            "</p>\n" +
            "</li>\n" +
            "<li class='icon--sight--pin--before js-poi nearby-pois__poi' data-latitude='15.87713672' data-longitude='108.3292194' data-topic='sight'>\n" +
            "<a class='nearby-pois__title copy--h4' href='/vietnam/central-vietnam/hoi-an/sights/architecture/quan-thang-house'>\n" +
            "Quan Thang House\n" +
            "</a>\n" +
            "<p class='nearby-pois__description'>\n" +
            "\n" +
            "This house is three centuries old and was built by a Chinese captain. As usual, the architecture includes Japanese and Chinese elements....\n" +
            "</p>\n" +
            "</li>\n" +
            "<li class='icon--restaurant--pin--before js-poi nearby-pois__poi' data-latitude='15.876613' data-longitude='108.329337' data-topic='restaurant'>\n" +
            "<a class='nearby-pois__title copy--h4' href='/vietnam/central-vietnam/hoi-an/restaurants/vietnamese/green-mango'>\n" +
            "Green Mango\n" +
            "</a>\n" +
            "<p class='nearby-pois__description'>\n" +
            "\n" +
            "The setting, inside one of Hoi An&#39;s most impressive traditional wooden houses, is beautiful, and the accomplished cooking (both Western...\n" +
            "</p>\n" +
            "</li>\n" +
            "</ul>\n" +
            "</div>\n" +
            "\n" +
            "<div class='js-preloader preloader'>\n" +
            "<div class='preloader__disc'>\n" +
            "<div class='preloader__disc--coloured preloader__disc--blue'></div>\n" +
            "</div>\n" +
            "<div class='preloader__disc'>\n" +
            "<div class='preloader__disc--coloured preloader__disc--red'></div>\n" +
            "</div>\n" +
            "<div class='preloader__disc'>\n" +
            "<div class='preloader__disc--coloured preloader__disc--green'></div>\n" +
            "</div>\n" +
            "<div class='preloader__disc'>\n" +
            "<div class='preloader__disc--coloured preloader__disc--orange'></div>\n" +
            "</div>\n" +
            "<div class='preloader__cover'></div>\n" +
            "</div>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "<div class='js-poi-map-tooltip'>\n" +
            "<div class='tooltip tooltip--above icon--tapered-arrow-down--after icon--white--after'>\n" +
            "<h5 class='tooltip__header copy--body'>\n" +
            "<div class='tooltip__location-icon icon--place--pin icon--lp-blue'></div>\n" +
            "Location\n" +
            "</h5>\n" +
            "<p class='tooltip__content'>\n" +
            "64 Ð Tran Phu\n" +
            "<span class='nearby-places'>\n" +
            "&middot;\n" +
            "<a class='copy--caption js-media-gallery-resizer js-toggle-active' data-toggle-class='is-expanded' data-toggle-target='.js-will-expand'>interesting places nearby</a>\n" +
            "</span>\n" +
            "</p>\n" +
            "</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "<div class='media-gallery__tabs'>\n" +
            "<a class='tab icon--white--before icon--place--pin--before js-tab-trigger' href='#js-tab-map'>\n" +
            "Map\n" +
            "</a>\n" +
            "</div>\n" +
            "</div>\n" +
            "\n" +
            "</div>\n" +
            "<div class='col--one-whole wv--col--two-fifths col--right js-will-move'>\n" +
            "<div class='ttd__aside ttd__aside--atlas js-ttd__aside' role='complimentary'>\n" +
            "<h2 class='accessibility'>\n" +
            "Chinese All-Community Assembly Hall information\n" +
            "</h2>\n" +
            "<div class='grid-wrapper--20'>\n" +
            "<div class='col--one-whole mv--col--one-half wv--col--one-whole'>\n" +
            "<dl class='info-list'>\n" +
            "<dt class='copy--h3 info-list__title text-icon icon--place--pin--before icon--lp-blue--before'>\n" +
            "Location\n" +
            "</dt>\n" +
            "<dd class='info-list__content copy--meta'>\n" +
            "<a href=\"/vietnam/central-vietnam/hoi-an\">Hoi An</a>\n" +
            ", \n" +
            "<a href=\"/vietnam\">Vietnam</a>\n" +
            "\n" +
            "</dd>\n" +
            "<dt class='copy--h3 info-list__title text-icon icon--map--before icon--lp-blue--before'>\n" +
            "Address\n" +
            "</dt>\n" +
            "<dd class='info-list__content copy--meta'>\n" +
            "64 Ð Tran Phu\n" +
            "</dd>\n" +
            "<dt class='copy--h3 info-list__title text-icon icon--contact--before icon--lp-blue--before'>\n" +
            "Telephone \n" +
            "</dt>\n" +
            "<dd class='info-list__content copy--meta'>\n" +
            "<a class='tel' href='tel:+84 510 510 861 935'>\n" +
            "+84 510 510 861 935\n" +
            "</a>\n" +
            "</dd>\n" +
            "</dl>\n" +
            "</div>\n" +
            "<div class='col--one-whole mv--col--one-half wv--col--one-whole'>\n" +
            "<dl class='info-list'>\n" +
            "<dt class='copy--h3 info-list__title text-icon icon--time--before icon--lp-blue--before'>\n" +
            "Opening hours\n" +
            "</dt>\n" +
            "<dd class='info-list__content copy--meta'>\n" +
            "8am-5pm\n" +
            "</dd>\n" +
            "<dt class='copy--h3 info-list__title text-icon icon--pen--line--before icon--lp-blue--before'>\n" +
            "Something wrong?\n" +
            "<dd class='info-list__content copy--meta'>\n" +
            "<a class='break-text' href='http://www.lonelyplanet.com/contact/business_listings/new_correction?business_listing%5Bbusiness_name%5D=Chinese+All-Community+Assembly+Hall&amp;business_listing%5Bbusiness_type%5D=sights&amp;business_listing%5Bcity%5D=Hoi+An%2C+Vietnam&amp;business_listing%5Bemail%5D=&amp;business_listing%5Bneighbourhood%5D=&amp;business_listing%5Boperating_hours%5D=8am-5pm&amp;business_listing%5Bplace_full_name%5D=Asia+-%3E+Southeast+Asia+-%3E+Vietnam%2C+Cambodia%2C+Laos+%26+Northern+Thailand+-%3E+Vietnam+-%3E+Hue%2C+Hoi+An+%26+Central+Vietnam+-%3E+Central+Vietnam+-%3E+Hoi+An&amp;business_listing%5Bplace_id%5D=357874&amp;business_listing%5Bpoi_id%5D=442870&amp;business_listing%5Bpostal_code%5D=&amp;business_listing%5Bstreet%5D=64+%C3%90+Tran+Phu&amp;business_listing%5Btelephone%5D=%2B84+510+510+861+935&amp;business_listing%5Btypical_prices%5D=&amp;business_listing%5Bwebsite%5D=&amp;poi_url=http%3A%2F%2Fwww.lonelyplanet.com%2Fvietnam%2Fcentral-vietnam%2Fhoi-an%2Fsights%2Farchitecture%2Fchinese-all-community-assembly-hall'>\n" +
            "Submit a correction\n" +
            "</a>\n" +
            "</dd>\n" +
            "</dt>\n" +
            "</dl>\n" +
            "</div>\n" +
            "</div>\n" +
            "<div class='ad-mpu wv--block'>\n" +
            "<div class='adunit adunit--mpu' data-size-mapping='mpu' data-targeting='{\"position\":null}'></div>\n" +
            "\n" +
            "</div>\n" +
            "</div>\n" +
            "\n" +
            "</div>\n" +
            "<div class='col--one-whole wv--col--three-fifths'>\n" +
            "<div class='ttd__content context--content copy--body'>\n" +
            "<div class='ttd__section ttd__section--description'>\n" +
            "<p>\n" +
            "<p>Founded in 1773, this assembly hall was used by Fujian, Cantonese, Hainan, Chaozhou and Hakka congregations in Hoi An. To the right of the entrance are portraits of Chinese resistance heroes in Vietnam who died during WWII. The well-restored main temple is a total assault on the senses, with great smoking incense spirals, demonic-looking deities, dragons and lashings of red lacquer – it’s dedicated to Thien Hau.</p>\n" +
            "</p>\n" +
            "</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "<div class='card--page__content card--page__content--social'>\n" +
            "<div class='sharing'>\n" +
            "<div class='mv--split--left sharing__nav nav--inline' id='js-social'>\n" +
            "<div class='nav--inline'>\n" +
            "<div aria-label='Share this page' class='social' role='complementary'>\n" +
            "<a class='social__item social__item--facebook js-facebook-share' data-lpa-action='facebook' data-lpa-category='share' data-lpa-label='https://www.lonelyplanet.com/vietnam/central-vietnam/hoi-an/sights/architecture/chinese-all-community-assembly-hall' href='https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fwww.lonelyplanet.com%2Fvietnam%2Fcentral-vietnam%2Fhoi-an%2Fsights%2Farchitecture%2Fchinese-all-community-assembly-hall' target='_blank'>\n" +
            "<span class='accessibility'>Share on Facebook</span>\n" +
            "<span class='social__icon icon--facebook icon--white'></span>\n" +
            "</a>\n" +
            "<a class='social__item social__item--twitter js-twitter-share' data-lpa-action='twitter' data-lpa-category='share' data-lpa-label='https://www.lonelyplanet.com/vietnam/central-vietnam/hoi-an/sights/architecture/chinese-all-community-assembly-hall' href='https://twitter.com/intent/tweet?text=Chinese All-Community Assembly Hall https://www.lonelyplanet.com/vietnam/central-vietnam/hoi-an/sights/architecture/chinese-all-community-assembly-hall via @lonelyplanet&amp;hashtags=[]' target='_blank'>\n" +
            "<span class='accessibility'>Share on Twitter</span>\n" +
            "<span class='social__icon icon--twitter icon--white'></span>\n" +
            "</a>\n" +
            "<a class='social__item social__item--google-plus js-google-plus-share' data-lpa-action='google-plus' data-lpa-category='share' data-lpa-label='https://www.lonelyplanet.com/vietnam/central-vietnam/hoi-an/sights/architecture/chinese-all-community-assembly-hall' href='https://plus.google.com/share?url=https%3A%2F%2Fwww.lonelyplanet.com%2Fvietnam%2Fcentral-vietnam%2Fhoi-an%2Fsights%2Farchitecture%2Fchinese-all-community-assembly-hall' target='_blank'>\n" +
            "<span class='accessibility'>Share on Google+</span>\n" +
            "<span class='social__icon icon--google-plus icon--white'></span>\n" +
            "</a>\n" +
            "<a class='social__item social__item--stumbleupon js-stumbleupon-share' data-lpa-action='stumbleupon' data-lpa-category='share' data-lpa-label='https://www.lonelyplanet.com/vietnam/central-vietnam/hoi-an/sights/architecture/chinese-all-community-assembly-hall' href='http://www.stumbleupon.com/submit?url=https%3A%2F%2Fwww.lonelyplanet.com%2Fvietnam%2Fcentral-vietnam%2Fhoi-an%2Fsights%2Farchitecture%2Fchinese-all-community-assembly-hall' target='_blank'>\n" +
            "<span class='accessibility'>Share on Stumbleupon</span>\n" +
            "<span class='social__icon icon--stumbleupon icon--white'></span>\n" +
            "</a>\n" +
            "</div>\n" +
            "</div>\n" +
            "\n" +
            "</div>\n" +
            "<div class='mv--block split--right'>\n" +
            "<div class='nav--inline'>\n" +
            "<span class='sharing__label nav__item'>Send this</span>\n" +
            "<div class='nav__item sharing__nav__item tooltip--hover'>\n" +
            "<a class='sharing__icon icon--print icon--body-grey' href='#' onclick='javascript:window.print();return false;'></a>\n" +
            "<div class='tooltip tooltip--above icon--tapered-arrow-down--after'>Print</div>\n" +
            "</div>\n" +
            "<div class='nav__item sharing__nav__item tooltip--hover'>\n" +
            "<a class='sharing__icon icon--envelope icon--body-grey js-mailto-share' data-lpa-action='email' data-lpa-category='share' data-lpa-label='https://www.lonelyplanet.com/vietnam/central-vietnam/hoi-an/sights/architecture/chinese-all-community-assembly-hall' href='mailto:?subject=Chinese All-Community Assembly Hall&amp;body=https://www.lonelyplanet.com/vietnam/central-vietnam/hoi-an/sights/architecture/chinese-all-community-assembly-hall'></a>\n" +
            "<div class='tooltip tooltip--above icon--tapered-arrow-down--after'>Email</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "\n" +
            "</div>\n" +
            "</div>\n" +
            "\n" +
            "\n" +
            "</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "\n" +
            "\n" +
            "\n" +
            "</div>\n" +
            "<div class='row row--footer row--breadcrumbs' id='js-breadcrumbs'>\n" +
            "<div class='row__inner row__inner--footer'>\n" +
            "<div class='nav--inline'><a class='nav__item nav__item--breadcrumbs--home icon--home' href='/'>Home</a><a class='nav__item js-nav-item nav__item--breadcrumbs' href='http://www.lonelyplanet.com/asia'>Asia</a><a class='nav__item js-nav-item nav__item--breadcrumbs' href='http://www.lonelyplanet.com/southeast-asia'>Southeast Asia</a><a class='nav__item js-nav-item nav__item--breadcrumbs' href='http://www.lonelyplanet.com/vietnam-cambodia-laos-northern-thailand'>Vietnam, Cambodia, Laos &amp; Northern Thailand</a><a class='nav__item js-nav-item nav__item--breadcrumbs' href='http://www.lonelyplanet.com/vietnam'>Vietnam</a><a class='nav__item js-nav-item nav__item--breadcrumbs' href='http://www.lonelyplanet.com/vietnam/hue-hoi-an-central-vietnam'>Hue, Hoi An &amp; Central Vietnam</a><a class='nav__item js-nav-item nav__item--breadcrumbs' href='http://www.lonelyplanet.com/vietnam/central-vietnam'>Central Vietnam</a><a class='nav__item js-nav-item nav__item--breadcrumbs' href='http://www.lonelyplanet.com/vietnam/central-vietnam/hoi-an'>Hoi An</a><a class='nav__item js-nav-item nav__item--breadcrumbs' href='http://www.lonelyplanet.com/vietnam/central-vietnam/hoi-an/sights'>Sights in Hoi An</a><span class='current js-nav-item nav__item nav__item--breadcrumbs'>Chinese All-Community Assembly Hall</span><div class='google-breadcrumbs is-hidden'>\n" +
            "<div id='google-breadcrumb-0' itemprop='child' itemref='google-breadcrumb-1' itemscope itemtype='http://data-vocabulary.org/Breadcrumb'>\n" +
            "<a href='http://www.lonelyplanet.com/asia' itemprop='url'>\n" +
            "<span itemprop='title'>\n" +
            "Asia\n" +
            "</span>\n" +
            "</a>\n" +
            "</div>\n" +
            "<div id='google-breadcrumb-1' itemprop='child' itemref='google-breadcrumb-2' itemscope itemtype='http://data-vocabulary.org/Breadcrumb'>\n" +
            "<a href='http://www.lonelyplanet.com/southeast-asia' itemprop='url'>\n" +
            "<span itemprop='title'>\n" +
            "Southeast Asia\n" +
            "</span>\n" +
            "</a>\n" +
            "</div>\n" +
            "<div id='google-breadcrumb-2' itemprop='child' itemref='google-breadcrumb-3' itemscope itemtype='http://data-vocabulary.org/Breadcrumb'>\n" +
            "<a href='http://www.lonelyplanet.com/vietnam-cambodia-laos-northern-thailand' itemprop='url'>\n" +
            "<span itemprop='title'>\n" +
            "Vietnam, Cambodia, Laos &amp; Northern Thailand\n" +
            "</span>\n" +
            "</a>\n" +
            "</div>\n" +
            "<div id='google-breadcrumb-3' itemprop='child' itemref='google-breadcrumb-4' itemscope itemtype='http://data-vocabulary.org/Breadcrumb'>\n" +
            "<a href='http://www.lonelyplanet.com/vietnam' itemprop='url'>\n" +
            "<span itemprop='title'>\n" +
            "Vietnam\n" +
            "</span>\n" +
            "</a>\n" +
            "</div>\n" +
            "<div id='google-breadcrumb-4' itemprop='child' itemref='google-breadcrumb-5' itemscope itemtype='http://data-vocabulary.org/Breadcrumb'>\n" +
            "<a href='http://www.lonelyplanet.com/vietnam/hue-hoi-an-central-vietnam' itemprop='url'>\n" +
            "<span itemprop='title'>\n" +
            "Hue, Hoi An &amp; Central Vietnam\n" +
            "</span>\n" +
            "</a>\n" +
            "</div>\n" +
            "<div id='google-breadcrumb-5' itemprop='child' itemref='google-breadcrumb-6' itemscope itemtype='http://data-vocabulary.org/Breadcrumb'>\n" +
            "<a href='http://www.lonelyplanet.com/vietnam/central-vietnam' itemprop='url'>\n" +
            "<span itemprop='title'>\n" +
            "Central Vietnam\n" +
            "</span>\n" +
            "</a>\n" +
            "</div>\n" +
            "<div id='google-breadcrumb-6' itemprop='child' itemscope itemtype='http://data-vocabulary.org/Breadcrumb'>\n" +
            "<a href='http://www.lonelyplanet.com/vietnam/central-vietnam/hoi-an' itemprop='url'>\n" +
            "<span itemprop='title'>\n" +
            "Hoi An\n" +
            "</span>\n" +
            "</a>\n" +
            "</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "\n" +
            "</div>\n" +
            "</div>\n" +
            "\n" +
            "\n" +
            "<div class='wrapper--footer js-wrapper'>\n" +
            "<nav aria-label='Sitemap' class='row row--sitemap' id='js-footer-nav' itemscope='itemscope' itemtype='http://schema.org/SiteNavigationElement' role='navigation'>\n" +
            "<h6 class='accessibility'>Sitemap</h6>\n" +
            "<div class='row__inner row--sitemap__inner'>\n" +
            "<div class='col--footer--last'>\n" +
            "<span class='title--footer title--list--footer'>\n" +
            "Newsletter sign up\n" +
            "</span>\n" +
            "<p class='nav__item--footer'>\n" +
            "<strong>\n" +
            "Subscribe now\n" +
            "</strong>\n" +
            "and receive a\n" +
            "<strong>\n" +
            "20% discount\n" +
            "</strong>\n" +
            "on your next guidebook purchase\n" +
            "</p>\n" +
            "<form action='//www.lonelyplanet.com/newsletter' class='newsletter-footer js-newsletter-footer' method='post'>\n" +
            "<input name='authenticity_token' type='hidden' value='/A7Rv39NW9d8htrZHkKr2iil3jS1TOITVfdNAU6yBSQ='>\n" +
            "<label class='polyfill--placeholder newsletter-footer__placeholder'>Email</label>\n" +
            "<input autocomplete='off' class='input--small--newsletter' id='newsletter-email' name='sailthru[email]' placeholder='your@email.com' required='required' type='text'>\n" +
            "<input name='sailthru[source]' type='hidden' value='homepagefooter'>\n" +
            "<input name='sailthru[email_template]' type='hidden' value='Welcome email'>\n" +
            "<div class='dropdown dropdown--country js-select-group-manager'>\n" +
            "<label class='dropdown__value dropdown__value--country js-select-overlay icon--chevron-down--after' for='select-country'>\n" +
            "Select country\n" +
            "</label>\n" +
            "<select class='dropdown__select js-select' id='select-country' name='sailthru[country]'>\n" +
            "<option value='Australia'>\n" +
            "Australia\n" +
            "</option>\n" +
            "<option value='United Kingdom'>\n" +
            "United Kingdom\n" +
            "</option>\n" +
            "<option value='United States'>\n" +
            "United States\n" +
            "</option>\n" +
            "<option value='Afghanistan'>\n" +
            "Afghanistan\n" +
            "</option>\n" +
            "<option value='Aland Islands'>\n" +
            "Aland Islands\n" +
            "</option>\n" +
            "<option value='Albania'>\n" +
            "Albania\n" +
            "</option>\n" +
            "<option value='Algeria'>\n" +
            "Algeria\n" +
            "</option>\n" +
            "<option value='American Samoa'>\n" +
            "American Samoa\n" +
            "</option>\n" +
            "<option value='Andorra'>\n" +
            "Andorra\n" +
            "</option>\n" +
            "<option value='Angola'>\n" +
            "Angola\n" +
            "</option>\n" +
            "<option value='Anguilla'>\n" +
            "Anguilla\n" +
            "</option>\n" +
            "<option value='Antarctica'>\n" +
            "Antarctica\n" +
            "</option>\n" +
            "<option value='Antigua And Barbuda'>\n" +
            "Antigua And Barbuda\n" +
            "</option>\n" +
            "<option value='Argentina'>\n" +
            "Argentina\n" +
            "</option>\n" +
            "<option value='Armenia'>\n" +
            "Armenia\n" +
            "</option>\n" +
            "<option value='Aruba'>\n" +
            "Aruba\n" +
            "</option>\n" +
            "<option value='Austria'>\n" +
            "Austria\n" +
            "</option>\n" +
            "<option value='Azerbaijan'>\n" +
            "Azerbaijan\n" +
            "</option>\n" +
            "<option value='Bahamas'>\n" +
            "Bahamas\n" +
            "</option>\n" +
            "<option value='Bahrain'>\n" +
            "Bahrain\n" +
            "</option>\n" +
            "<option value='Bangladesh'>\n" +
            "Bangladesh\n" +
            "</option>\n" +
            "<option value='Barbados'>\n" +
            "Barbados\n" +
            "</option>\n" +
            "<option value='Belarus'>\n" +
            "Belarus\n" +
            "</option>\n" +
            "<option value='Belgium'>\n" +
            "Belgium\n" +
            "</option>\n" +
            "<option value='Belize'>\n" +
            "Belize\n" +
            "</option>\n" +
            "<option value='Benin'>\n" +
            "Benin\n" +
            "</option>\n" +
            "<option value='Bermuda'>\n" +
            "Bermuda\n" +
            "</option>\n" +
            "<option value='Bhutan'>\n" +
            "Bhutan\n" +
            "</option>\n" +
            "<option value='Bolivia'>\n" +
            "Bolivia\n" +
            "</option>\n" +
            "<option value='Bonaire'>\n" +
            "Bonaire\n" +
            "</option>\n" +
            "<option value='Bosnia and Herzegovina'>\n" +
            "Bosnia and Herzegovina\n" +
            "</option>\n" +
            "<option value='Botswana'>\n" +
            "Botswana\n" +
            "</option>\n" +
            "<option value='Bouvet Island'>\n" +
            "Bouvet Island\n" +
            "</option>\n" +
            "<option value='Brazil'>\n" +
            "Brazil\n" +
            "</option>\n" +
            "<option value='British Indian Ocean Territory'>\n" +
            "British Indian Ocean Territory\n" +
            "</option>\n" +
            "<option value='British Virgin Islands'>\n" +
            "British Virgin Islands\n" +
            "</option>\n" +
            "<option value='Brunei Darussalam'>\n" +
            "Brunei Darussalam\n" +
            "</option>\n" +
            "<option value='Bulgaria'>\n" +
            "Bulgaria\n" +
            "</option>\n" +
            "<option value='Burkina Faso'>\n" +
            "Burkina Faso\n" +
            "</option>\n" +
            "<option value='Burundi'>\n" +
            "Burundi\n" +
            "</option>\n" +
            "<option value='Cambodia'>\n" +
            "Cambodia\n" +
            "</option>\n" +
            "<option value='Cameroon'>\n" +
            "Cameroon\n" +
            "</option>\n" +
            "<option value='Canada'>\n" +
            "Canada\n" +
            "</option>\n" +
            "<option value='Cape Verde'>\n" +
            "Cape Verde\n" +
            "</option>\n" +
            "<option value='Cayman Islands'>\n" +
            "Cayman Islands\n" +
            "</option>\n" +
            "<option value='Central African Republic'>\n" +
            "Central African Republic\n" +
            "</option>\n" +
            "<option value='Chad'>\n" +
            "Chad\n" +
            "</option>\n" +
            "<option value='Channel Islands, The'>\n" +
            "Channel Islands, The\n" +
            "</option>\n" +
            "<option value='Chile'>\n" +
            "Chile\n" +
            "</option>\n" +
            "<option value='China'>\n" +
            "China\n" +
            "</option>\n" +
            "<option value='Christmas Island'>\n" +
            "Christmas Island\n" +
            "</option>\n" +
            "<option value='Cocos (Keeling) Islands'>\n" +
            "Cocos (Keeling) Islands\n" +
            "</option>\n" +
            "<option value='Colombia'>\n" +
            "Colombia\n" +
            "</option>\n" +
            "<option value='Comoros'>\n" +
            "Comoros\n" +
            "</option>\n" +
            "<option value='Congo'>\n" +
            "Congo\n" +
            "</option>\n" +
            "<option value='Congo, The Democratic Republic of the'>\n" +
            "Congo, The Democratic Republic of the\n" +
            "</option>\n" +
            "<option value='Cook Islands'>\n" +
            "Cook Islands\n" +
            "</option>\n" +
            "<option value='Costa Rica'>\n" +
            "Costa Rica\n" +
            "</option>\n" +
            "<option value=\"Cote D'Ivoire\">\n" +
            "Cote D'Ivoire\n" +
            "</option>\n" +
            "<option value='Croatia'>\n" +
            "Croatia\n" +
            "</option>\n" +
            "<option value='Cuba'>\n" +
            "Cuba\n" +
            "</option>\n" +
            "<option value='Curacao'>\n" +
            "Curacao\n" +
            "</option>\n" +
            "<option value='Cyprus'>\n" +
            "Cyprus\n" +
            "</option>\n" +
            "<option value='Czech Republic'>\n" +
            "Czech Republic\n" +
            "</option>\n" +
            "<option value='Denmark'>\n" +
            "Denmark\n" +
            "</option>\n" +
            "<option value='Djibouti'>\n" +
            "Djibouti\n" +
            "</option>\n" +
            "<option value='Dominica'>\n" +
            "Dominica\n" +
            "</option>\n" +
            "<option value='Dominican Republic'>\n" +
            "Dominican Republic\n" +
            "</option>\n" +
            "<option value='East Timor'>\n" +
            "East Timor\n" +
            "</option>\n" +
            "<option value='Ecuador'>\n" +
            "Ecuador\n" +
            "</option>\n" +
            "<option value='Egypt'>\n" +
            "Egypt\n" +
            "</option>\n" +
            "<option value='El Salvador'>\n" +
            "El Salvador\n" +
            "</option>\n" +
            "<option value='England'>\n" +
            "England\n" +
            "</option>\n" +
            "<option value='Equatorial Guinea'>\n" +
            "Equatorial Guinea\n" +
            "</option>\n" +
            "<option value='Eritrea'>\n" +
            "Eritrea\n" +
            "</option>\n" +
            "<option value='Estonia'>\n" +
            "Estonia\n" +
            "</option>\n" +
            "<option value='Ethiopia'>\n" +
            "Ethiopia\n" +
            "</option>\n" +
            "<option value='Falkland Islands (Malvinas)'>\n" +
            "Falkland Islands (Malvinas)\n" +
            "</option>\n" +
            "<option value='Faroe Islands'>\n" +
            "Faroe Islands\n" +
            "</option>\n" +
            "<option value='Fiji'>\n" +
            "Fiji\n" +
            "</option>\n" +
            "<option value='Finland'>\n" +
            "Finland\n" +
            "</option>\n" +
            "<option value='France'>\n" +
            "France\n" +
            "</option>\n" +
            "<option value='French Guiana'>\n" +
            "French Guiana\n" +
            "</option>\n" +
            "<option value='French Polynesia'>\n" +
            "French Polynesia\n" +
            "</option>\n" +
            "<option value='French Southern Territories'>\n" +
            "French Southern Territories\n" +
            "</option>\n" +
            "<option value='Gabon'>\n" +
            "Gabon\n" +
            "</option>\n" +
            "<option value='Gambia'>\n" +
            "Gambia\n" +
            "</option>\n" +
            "<option value='Georgia'>\n" +
            "Georgia\n" +
            "</option>\n" +
            "<option value='Germany'>\n" +
            "Germany\n" +
            "</option>\n" +
            "<option value='Ghana'>\n" +
            "Ghana\n" +
            "</option>\n" +
            "<option value='Gibraltar'>\n" +
            "Gibraltar\n" +
            "</option>\n" +
            "<option value='Greece'>\n" +
            "Greece\n" +
            "</option>\n" +
            "<option value='Greenland'>\n" +
            "Greenland\n" +
            "</option>\n" +
            "<option value='Grenada'>\n" +
            "Grenada\n" +
            "</option>\n" +
            "<option value='Guadeloupe'>\n" +
            "Guadeloupe\n" +
            "</option>\n" +
            "<option value='Guam'>\n" +
            "Guam\n" +
            "</option>\n" +
            "<option value='Guatemala'>\n" +
            "Guatemala\n" +
            "</option>\n" +
            "<option value='Guernsey'>\n" +
            "Guernsey\n" +
            "</option>\n" +
            "<option value='Guinea'>\n" +
            "Guinea\n" +
            "</option>\n" +
            "<option value='Guinea-Bissau'>\n" +
            "Guinea-Bissau\n" +
            "</option>\n" +
            "<option value='Guyana'>\n" +
            "Guyana\n" +
            "</option>\n" +
            "<option value='Haiti'>\n" +
            "Haiti\n" +
            "</option>\n" +
            "<option value='Heard Island And Mcdonald Islands'>\n" +
            "Heard Island And Mcdonald Islands\n" +
            "</option>\n" +
            "<option value='Holy See (Vatican City State)'>\n" +
            "Holy See (Vatican City State)\n" +
            "</option>\n" +
            "<option value='Honduras'>\n" +
            "Honduras\n" +
            "</option>\n" +
            "<option value='Hong Kong'>\n" +
            "Hong Kong\n" +
            "</option>\n" +
            "<option value='Hungary'>\n" +
            "Hungary\n" +
            "</option>\n" +
            "<option value='Iceland'>\n" +
            "Iceland\n" +
            "</option>\n" +
            "<option value='India'>\n" +
            "India\n" +
            "</option>\n" +
            "<option value='Indonesia'>\n" +
            "Indonesia\n" +
            "</option>\n" +
            "<option value='Iran, Islamic Republic of'>\n" +
            "Iran, Islamic Republic of\n" +
            "</option>\n" +
            "<option value='Iraq'>\n" +
            "Iraq\n" +
            "</option>\n" +
            "<option value='Ireland'>\n" +
            "Ireland\n" +
            "</option>\n" +
            "<option value='Isle of Man'>\n" +
            "Isle of Man\n" +
            "</option>\n" +
            "<option value='Israel'>\n" +
            "Israel\n" +
            "</option>\n" +
            "<option value='Italy'>\n" +
            "Italy\n" +
            "</option>\n" +
            "<option value='Jamaica'>\n" +
            "Jamaica\n" +
            "</option>\n" +
            "<option value='Japan'>\n" +
            "Japan\n" +
            "</option>\n" +
            "<option value='Jersey'>\n" +
            "Jersey\n" +
            "</option>\n" +
            "<option value='Jordan'>\n" +
            "Jordan\n" +
            "</option>\n" +
            "<option value='Kazakhstan'>\n" +
            "Kazakhstan\n" +
            "</option>\n" +
            "<option value='Kenya'>\n" +
            "Kenya\n" +
            "</option>\n" +
            "<option value='Kiribati'>\n" +
            "Kiribati\n" +
            "</option>\n" +
            "<option value=\"Korea, Democratic People's Republic of\">\n" +
            "Korea, Democratic People's Republic of\n" +
            "</option>\n" +
            "<option value='Korea, Republic of'>\n" +
            "Korea, Republic of\n" +
            "</option>\n" +
            "<option value='Kuwait'>\n" +
            "Kuwait\n" +
            "</option>\n" +
            "<option value='Kyrgyzstan'>\n" +
            "Kyrgyzstan\n" +
            "</option>\n" +
            "<option value=\"Lao People's Democratic Republic\">\n" +
            "Lao People's Democratic Republic\n" +
            "</option>\n" +
            "<option value='Latvia'>\n" +
            "Latvia\n" +
            "</option>\n" +
            "<option value='Lebanon'>\n" +
            "Lebanon\n" +
            "</option>\n" +
            "<option value='Lesotho'>\n" +
            "Lesotho\n" +
            "</option>\n" +
            "<option value='Liberia'>\n" +
            "Liberia\n" +
            "</option>\n" +
            "<option value='Libyan Arab Jamahiriya'>\n" +
            "Libyan Arab Jamahiriya\n" +
            "</option>\n" +
            "<option value='Liechtenstein'>\n" +
            "Liechtenstein\n" +
            "</option>\n" +
            "<option value='Lithuania'>\n" +
            "Lithuania\n" +
            "</option>\n" +
            "<option value='Luxembourg'>\n" +
            "Luxembourg\n" +
            "</option>\n" +
            "<option value='Macao'>\n" +
            "Macao\n" +
            "</option>\n" +
            "<option value='Macedonia, The Former Yugoslav Republic of'>\n" +
            "Macedonia, The Former Yugoslav Republic of\n" +
            "</option>\n" +
            "<option value='Madagascar'>\n" +
            "Madagascar\n" +
            "</option>\n" +
            "<option value='Malawi'>\n" +
            "Malawi\n" +
            "</option>\n" +
            "<option value='Malaysia'>\n" +
            "Malaysia\n" +
            "</option>\n" +
            "<option value='Maldives'>\n" +
            "Maldives\n" +
            "</option>\n" +
            "<option value='Mali'>\n" +
            "Mali\n" +
            "</option>\n" +
            "<option value='Malta'>\n" +
            "Malta\n" +
            "</option>\n" +
            "<option value='Marshall Islands'>\n" +
            "Marshall Islands\n" +
            "</option>\n" +
            "<option value='Martinique'>\n" +
            "Martinique\n" +
            "</option>\n" +
            "<option value='Mauritania'>\n" +
            "Mauritania\n" +
            "</option>\n" +
            "<option value='Mauritius'>\n" +
            "Mauritius\n" +
            "</option>\n" +
            "<option value='Mayotte'>\n" +
            "Mayotte\n" +
            "</option>\n" +
            "<option value='Mexico'>\n" +
            "Mexico\n" +
            "</option>\n" +
            "<option value='Micronesia, Federated States of'>\n" +
            "Micronesia, Federated States of\n" +
            "</option>\n" +
            "<option value='Moldova, Republic of'>\n" +
            "Moldova, Republic of\n" +
            "</option>\n" +
            "<option value='Monaco'>\n" +
            "Monaco\n" +
            "</option>\n" +
            "<option value='Mongolia'>\n" +
            "Mongolia\n" +
            "</option>\n" +
            "<option value='Montenegro'>\n" +
            "Montenegro\n" +
            "</option>\n" +
            "<option value='Montserrat'>\n" +
            "Montserrat\n" +
            "</option>\n" +
            "<option value='Morocco'>\n" +
            "Morocco\n" +
            "</option>\n" +
            "<option value='Mozambique'>\n" +
            "Mozambique\n" +
            "</option>\n" +
            "<option value='Myanmar'>\n" +
            "Myanmar\n" +
            "</option>\n" +
            "<option value='Namibia'>\n" +
            "Namibia\n" +
            "</option>\n" +
            "<option value='Nauru'>\n" +
            "Nauru\n" +
            "</option>\n" +
            "<option value='Nepal'>\n" +
            "Nepal\n" +
            "</option>\n" +
            "<option value='Netherlands Antilles'>\n" +
            "Netherlands Antilles\n" +
            "</option>\n" +
            "<option value='Netherlands'>\n" +
            "Netherlands\n" +
            "</option>\n" +
            "<option value='New Caledonia'>\n" +
            "New Caledonia\n" +
            "</option>\n" +
            "<option value='New Guinea'>\n" +
            "New Guinea\n" +
            "</option>\n" +
            "<option value='New Zealand'>\n" +
            "New Zealand\n" +
            "</option>\n" +
            "<option value='Nicaragua'>\n" +
            "Nicaragua\n" +
            "</option>\n" +
            "<option value='Niger'>\n" +
            "Niger\n" +
            "</option>\n" +
            "<option value='Nigeria'>\n" +
            "Nigeria\n" +
            "</option>\n" +
            "<option value='Niue'>\n" +
            "Niue\n" +
            "</option>\n" +
            "<option value='Norfolk Island'>\n" +
            "Norfolk Island\n" +
            "</option>\n" +
            "<option value='North Korea'>\n" +
            "North Korea\n" +
            "</option>\n" +
            "<option value='Northern Ireland'>\n" +
            "Northern Ireland\n" +
            "</option>\n" +
            "<option value='Northern Mariana Islands'>\n" +
            "Northern Mariana Islands\n" +
            "</option>\n" +
            "<option value='Norway'>\n" +
            "Norway\n" +
            "</option>\n" +
            "<option value='Oman'>\n" +
            "Oman\n" +
            "</option>\n" +
            "<option value='Pakistan'>\n" +
            "Pakistan\n" +
            "</option>\n" +
            "<option value='Palau'>\n" +
            "Palau\n" +
            "</option>\n" +
            "<option value='Palestinian Territory, Occupied'>\n" +
            "Palestinian Territory, Occupied\n" +
            "</option>\n" +
            "<option value='Panama'>\n" +
            "Panama\n" +
            "</option>\n" +
            "<option value='Papua New Guinea'>\n" +
            "Papua New Guinea\n" +
            "</option>\n" +
            "<option value='Paraguay'>\n" +
            "Paraguay\n" +
            "</option>\n" +
            "<option value='Peru'>\n" +
            "Peru\n" +
            "</option>\n" +
            "<option value='Philippines'>\n" +
            "Philippines\n" +
            "</option>\n" +
            "<option value='Pitcairn'>\n" +
            "Pitcairn\n" +
            "</option>\n" +
            "<option value='Poland'>\n" +
            "Poland\n" +
            "</option>\n" +
            "<option value='Portugal'>\n" +
            "Portugal\n" +
            "</option>\n" +
            "<option value='Puerto Rico'>\n" +
            "Puerto Rico\n" +
            "</option>\n" +
            "<option value='Qatar'>\n" +
            "Qatar\n" +
            "</option>\n" +
            "<option value='Reunion'>\n" +
            "Reunion\n" +
            "</option>\n" +
            "<option value='Romania'>\n" +
            "Romania\n" +
            "</option>\n" +
            "<option value='Russian Federation'>\n" +
            "Russian Federation\n" +
            "</option>\n" +
            "<option value='Rwanda'>\n" +
            "Rwanda\n" +
            "</option>\n" +
            "<option value='Saba'>\n" +
            "Saba\n" +
            "</option>\n" +
            "<option value='Saint Eustatius'>\n" +
            "Saint Eustatius\n" +
            "</option>\n" +
            "<option value='Saint Barthelemy'>\n" +
            "Saint Barthelemy\n" +
            "</option>\n" +
            "<option value='Saint Helena'>\n" +
            "Saint Helena\n" +
            "</option>\n" +
            "<option value='Saint Kitts and Nevis'>\n" +
            "Saint Kitts and Nevis\n" +
            "</option>\n" +
            "<option value='Saint Lucia'>\n" +
            "Saint Lucia\n" +
            "</option>\n" +
            "<option value='Saint Martin (France)'>\n" +
            "Saint Martin (France)\n" +
            "</option>\n" +
            "<option value='Saint Pierre and Miquelon'>\n" +
            "Saint Pierre and Miquelon\n" +
            "</option>\n" +
            "<option value='Saint Vincent and the Grenadines'>\n" +
            "Saint Vincent and the Grenadines\n" +
            "</option>\n" +
            "<option value='Samoa'>\n" +
            "Samoa\n" +
            "</option>\n" +
            "<option value='San Marino'>\n" +
            "San Marino\n" +
            "</option>\n" +
            "<option value='Sao Tome and Principe'>\n" +
            "Sao Tome and Principe\n" +
            "</option>\n" +
            "<option value='Saudi Arabia'>\n" +
            "Saudi Arabia\n" +
            "</option>\n" +
            "<option value='Scotland'>\n" +
            "Scotland\n" +
            "</option>\n" +
            "<option value='Senegal'>\n" +
            "Senegal\n" +
            "</option>\n" +
            "<option value='Serbia'>\n" +
            "Serbia\n" +
            "</option>\n" +
            "<option value='Seychelles'>\n" +
            "Seychelles\n" +
            "</option>\n" +
            "<option value='Sierra Leone'>\n" +
            "Sierra Leone\n" +
            "</option>\n" +
            "<option value='Singapore'>\n" +
            "Singapore\n" +
            "</option>\n" +
            "<option value='Slovakia'>\n" +
            "Slovakia\n" +
            "</option>\n" +
            "<option value='Slovenia'>\n" +
            "Slovenia\n" +
            "</option>\n" +
            "<option value='Solomon Islands'>\n" +
            "Solomon Islands\n" +
            "</option>\n" +
            "<option value='Somalia'>\n" +
            "Somalia\n" +
            "</option>\n" +
            "<option value='South Africa'>\n" +
            "South Africa\n" +
            "</option>\n" +
            "<option value='South Korea'>\n" +
            "South Korea\n" +
            "</option>\n" +
            "<option value='South Georgia and the South Sandwich Islands'>\n" +
            "South Georgia and the South Sandwich Islands\n" +
            "</option>\n" +
            "<option value='Spain'>\n" +
            "Spain\n" +
            "</option>\n" +
            "<option value='Sri Lanka'>\n" +
            "Sri Lanka\n" +
            "</option>\n" +
            "<option value='Sudan'>\n" +
            "Sudan\n" +
            "</option>\n" +
            "<option value='Suriname'>\n" +
            "Suriname\n" +
            "</option>\n" +
            "<option value='Svalbard and Jan Mayen'>\n" +
            "Svalbard and Jan Mayen\n" +
            "</option>\n" +
            "<option value='Swaziland'>\n" +
            "Swaziland\n" +
            "</option>\n" +
            "<option value='Sweden'>\n" +
            "Sweden\n" +
            "</option>\n" +
            "<option value='Switzerland'>\n" +
            "Switzerland\n" +
            "</option>\n" +
            "<option value='Syrian Arab Republic'>\n" +
            "Syrian Arab Republic\n" +
            "</option>\n" +
            "<option value='Taiwan'>\n" +
            "Taiwan\n" +
            "</option>\n" +
            "<option value='Tajikistan'>\n" +
            "Tajikistan\n" +
            "</option>\n" +
            "<option value='Tanzania, United Republic of'>\n" +
            "Tanzania, United Republic of\n" +
            "</option>\n" +
            "<option value='Thailand'>\n" +
            "Thailand\n" +
            "</option>\n" +
            "<option value='Timor-Leste'>\n" +
            "Timor-Leste\n" +
            "</option>\n" +
            "<option value='Togo'>\n" +
            "Togo\n" +
            "</option>\n" +
            "<option value='Tokelau'>\n" +
            "Tokelau\n" +
            "</option>\n" +
            "<option value='Tonga'>\n" +
            "Tonga\n" +
            "</option>\n" +
            "<option value='Trinidad and Tobago'>\n" +
            "Trinidad and Tobago\n" +
            "</option>\n" +
            "<option value='Tunisia'>\n" +
            "Tunisia\n" +
            "</option>\n" +
            "<option value='Turkey'>\n" +
            "Turkey\n" +
            "</option>\n" +
            "<option value='Turkmenistan'>\n" +
            "Turkmenistan\n" +
            "</option>\n" +
            "<option value='Turks and Caicos Islands'>\n" +
            "Turks and Caicos Islands\n" +
            "</option>\n" +
            "<option value='Tuvalu'>\n" +
            "Tuvalu\n" +
            "</option>\n" +
            "<option value='Uganda'>\n" +
            "Uganda\n" +
            "</option>\n" +
            "<option value='Ukraine'>\n" +
            "Ukraine\n" +
            "</option>\n" +
            "<option value='United Arab Emirates'>\n" +
            "United Arab Emirates\n" +
            "</option>\n" +
            "<option value='United States Minor Outlying Islands'>\n" +
            "United States Minor Outlying Islands\n" +
            "</option>\n" +
            "<option value='Uruguay'>\n" +
            "Uruguay\n" +
            "</option>\n" +
            "<option value='Uzbekistan'>\n" +
            "Uzbekistan\n" +
            "</option>\n" +
            "<option value='Vanuatu'>\n" +
            "Vanuatu\n" +
            "</option>\n" +
            "<option value='Venezuela'>\n" +
            "Venezuela\n" +
            "</option>\n" +
            "<option value='Viet Nam'>\n" +
            "Viet Nam\n" +
            "</option>\n" +
            "<option value='Virgin Islands, British'>\n" +
            "Virgin Islands, British\n" +
            "</option>\n" +
            "<option value='Virgin Islands, U.S.'>\n" +
            "Virgin Islands, U.S.\n" +
            "</option>\n" +
            "<option value='Wallis and Futuna'>\n" +
            "Wallis and Futuna\n" +
            "</option>\n" +
            "<option value='Western Sahara'>\n" +
            "Western Sahara\n" +
            "</option>\n" +
            "<option value='Yemen'>\n" +
            "Yemen\n" +
            "</option>\n" +
            "<option value='Zaire'>\n" +
            "Zaire\n" +
            "</option>\n" +
            "<option value='Zambia'>\n" +
            "Zambia\n" +
            "</option>\n" +
            "<option value='Zimbabwe'>\n" +
            "Zimbabwe\n" +
            "</option>\n" +
            "</select>\n" +
            "</div>\n" +
            "<input class='btn btn--green btn--large newsletter-footer__btn' data-lpa-action='newsletter' data-lpa-category='account' title='Subscribe Newsletter' type='submit' value='Sign up'>\n" +
            "</form>\n" +
            "</div>\n" +
            "<div class='col--footer'>\n" +
            "<input class='accordion__input' id='ac-destinations' type='checkbox'>\n" +
            "<div class='nav--stacked accordion__target accordion__target--mid'>\n" +
            "<label class='accordion__title accordion__title--footer icon--chevron-down--after' for='ac-destinations'></label>\n" +
            "<a class='js-nav-item title--footer title--list--footer' href='http://www.lonelyplanet.com/destinations' itemprop='url'>\n" +
            "<span itemprop='name'>\n" +
            "Destinations\n" +
            "</span>\n" +
            "</a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/africa' itemprop='url'><span itemprop='name'>\n" +
            "Africa</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/antarctica' itemprop='url'><span itemprop='name'>\n" +
            "Antarctica</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/asia' itemprop='url'><span itemprop='name'>\n" +
            "Asia</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/caribbean' itemprop='url'><span itemprop='name'>\n" +
            "Caribbean Islands</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/central-america' itemprop='url'><span itemprop='name'>\n" +
            "Central America</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/europe' itemprop='url'><span itemprop='name'>\n" +
            "Europe</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/middle-east' itemprop='url'><span itemprop='name'>\n" +
            "Middle East</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/north-america' itemprop='url'><span itemprop='name'>\n" +
            "North America</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/pacific' itemprop='url'><span itemprop='name'>\n" +
            "Pacific</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/south-america' itemprop='url'><span itemprop='name'>\n" +
            "South America</span></a>\n" +
            "</div>\n" +
            "</div>\n" +
            "<div class='col--footer'>\n" +
            "<input class='accordion__input' id='ac-shop' type='checkbox'>\n" +
            "<div class='nav--stacked accordion__target accordion__target--mid'>\n" +
            "<label class='accordion__title accordion__title--footer icon--chevron-down--after' for='ac-shop'></label>\n" +
            "<a class='js-nav-item title--footer title--list--footer' href='http://www.lonelyplanet.com/shop' itemprop='url'>\n" +
            "<span itemprop='name'>\n" +
            "Shop\n" +
            "</span>\n" +
            "</a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/shop/destination-guides' itemprop='url'><span itemprop='name'>\n" +
            "Destination guides</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/shop/ebooks' itemprop='url'><span itemprop='name'>\n" +
            "eBooks</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/shop/pictorials-and-gifts' itemprop='url'><span itemprop='name'>\n" +
            "Pictorial &amp; gifts</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/shop/phrasebooks' itemprop='url'><span itemprop='name'>\n" +
            "Phrasebooks</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/shop/childrens-books' itemprop='url'><span itemprop='name'>\n" +
            "Lonely Planet Kids</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/shop/special-offers' itemprop='url'><span itemprop='name'>\n" +
            "Special offers</span></a>\n" +
            "</div>\n" +
            "</div>\n" +
            "<div class='col--footer'>\n" +
            "<input class='accordion__input' id='ac-thorntree' type='checkbox'>\n" +
            "<div class='nav--stacked accordion__target accordion__target--mid'>\n" +
            "<label class='accordion__title accordion__title--footer icon--chevron-down--after' for='ac-thorntree'></label>\n" +
            "<a class='js-nav-item title--footer title--list--footer' href='http://www.lonelyplanet.com/thorntree' itemprop='url'>\n" +
            "<span itemprop='name'>\n" +
            "Thorn Tree Forum\n" +
            "</span>\n" +
            "</a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/thorntree/categories/country-forums' itemprop='url'><span itemprop='name'>\n" +
            "Country forums</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/thorntree/categories/talk-to-lonely-planet' itemprop='url'><span itemprop='name'>\n" +
            "Talk to Lonely Planet</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/thorntree/categories/interest-forums' itemprop='url'><span itemprop='name'>\n" +
            "Interest forums</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/thorntree/categories/sell-swap-meet-up' itemprop='url'><span itemprop='name'>\n" +
            "Travel buddies &amp; for sale</span></a>\n" +
            "</div>\n" +
            "</div>\n" +
            "<div class='col--footer'>\n" +
            "<input class='accordion__input' id='ac-interests' type='checkbox'>\n" +
            "<div class='nav--stacked accordion__target accordion__target--mid'>\n" +
            "<label class='accordion__title accordion__title--footer icon--chevron-down--after' for='ac-interests'></label>\n" +
            "<a class='js-nav-item title--footer title--list--footer' href='http://www.lonelyplanet.com/interests' itemprop='url'>\n" +
            "<span itemprop='name'>\n" +
            "Interests\n" +
            "</span>\n" +
            "</a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/adventure-travel' itemprop='url'><span itemprop='name'>\n" +
            "Adventure travel</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/beaches' itemprop='url'><span itemprop='name'>\n" +
            "Beaches</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/budget-travel' itemprop='url'><span itemprop='name'>\n" +
            "Budget travel</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/family-travel' itemprop='url'><span itemprop='name'>\n" +
            "Family travel</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/festivals-and-events' itemprop='url'><span itemprop='name'>\n" +
            "Festivals and events</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/food-and-drink' itemprop='url'><span itemprop='name'>\n" +
            "Food and drink</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/honeymoons-and-romance' itemprop='url'><span itemprop='name'>\n" +
            "Honeymoons and romance</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/luxury-travel' itemprop='url'><span itemprop='name'>\n" +
            "Luxury travel</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/round-the-world-travel' itemprop='url'><span itemprop='name'>\n" +
            "Round the world travel</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/wildlife-and-nature' itemprop='url'><span itemprop='name'>\n" +
            "Wildlife and nature</span></a>\n" +
            "</div>\n" +
            "</div>\n" +
            "<div class='col--footer'>\n" +
            "<input class='accordion__input' id='ac-bookings' type='checkbox'>\n" +
            "<div class='nav--stacked accordion__target accordion__target--mid'>\n" +
            "<label class='accordion__title accordion__title--footer icon--chevron-down--after' for='ac-bookings'></label>\n" +
            "<a class='js-nav-item title--footer title--list--footer' href='http://www.lonelyplanet.com/bookings' itemprop='url'>\n" +
            "<span itemprop='name'>\n" +
            "Travel Booking\n" +
            "</span>\n" +
            "</a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/hotels' itemprop='url'><span itemprop='name'>\n" +
            "Hotels</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/flights' itemprop='url'><span itemprop='name'>\n" +
            "Flights</span></a>\n" +
            "<a class='js-nav-item nav__item nav__item--sitemap nav__item--footer' href='http://www.lonelyplanet.com/travel-insurance' itemprop='url'><span itemprop='name'>\n" +
            "Insurance</span></a>\n" +
            "</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "</nav>\n" +
            "\n" +
            "<div class='row row--footer--about' role='contentinfo'>\n" +
            "<div class='row__inner row__inner--full-width'>\n" +
            "<div class='wv--split--left nav--inline'>\n" +
            "<a class='wv--inline-block nav__item row--footer--about__logo media--lp-logo icon--lp-logo' href='http://www.lonelyplanet.com'></a>\n" +
            "<nav aria-label='About Lonely Planet' class='nav__item wv--nav--inline' itemscope='itemscope' itemtype='http://schema.org/SiteNavigationElement' role='navigation'>\n" +
            "<h6 class='accessibility'>About Lonely Planet</h6>\n" +
            "<a class='nav__item nav__item--about' href='http://www.lonelyplanet.com/about/' itemprop='url'><span itemprop='name'>\n" +
            "About us</span></a>\n" +
            "<a class='nav__item nav__item--about' href='http://www.lonelyplanet.com/jobs/' itemprop='url'><span itemprop='name'>\n" +
            "Work for us</span></a>\n" +
            "<a class='nav__item nav__item--about' href='http://www.lonelyplanet.com/contact/' itemprop='url'><span itemprop='name'>\n" +
            "Contact us</span></a>\n" +
            "<a class='nav__item nav__item--about' href='http://www.lonelyplanet.com/press-trade-advertising/' itemprop='url'><span itemprop='name'>\n" +
            "Press, trade &amp; advertising</span></a>\n" +
            "<a class='nav__item nav__item--about' href='http://www.lonelyplanet.com/legal/website-terms/' itemprop='url'><span itemprop='name'>\n" +
            "Terms &amp; conditions</span></a>\n" +
            "<a class='nav__item nav__item--about' href='http://www.lonelyplanet.com/legal/privacy-policy/' itemprop='url'><span itemprop='name'>\n" +
            "Privacy policy</span></a>\n" +
            "</nav>\n" +
            "</div>\n" +
            "<div class='wv--split--right'>\n" +
            "<div class='nav--inline nav--social split--right__inner'>\n" +
            "<a class='wv--hidden row--footer--social__logo media--lp-logo icon--lp-logo' href='http://www.lonelyplanet.com'></a>\n" +
            "<nav aria-label='Social media links' class='nav--social__inner nav__item' itemscope='itemscope' itemtype='http://schema.org/SiteNavigationElement' role='navigation'>\n" +
            "<h6 class='accessibility'>Social media links</h6>\n" +
            "<div class='nav--inline'>\n" +
            "<a class='icon--twitter nav__item nav__item--social' href='https://twitter.com/lonelyplanet' itemprop='url'>\n" +
            "<span itemprop='name'>\n" +
            "Twitter\n" +
            "</span>\n" +
            "</a>\n" +
            "<a class='icon--facebook nav__item nav__item--social' href='https://www.facebook.com/lonelyplanet' itemprop='url'>\n" +
            "<span itemprop='name'>\n" +
            "Facebook\n" +
            "</span>\n" +
            "</a>\n" +
            "<a class='icon--google-plus nav__item nav__item--social' href='https://plus.google.com/+LonelyPlanet/' itemprop='url'>\n" +
            "<span itemprop='name'>\n" +
            "Google-plus\n" +
            "</span>\n" +
            "</a>\n" +
            "<a class='icon--flickr nav__item nav__item--social' href='http://www.flickr.com/groups/lonelyplanetpublications/' itemprop='url'>\n" +
            "<span itemprop='name'>\n" +
            "Flickr\n" +
            "</span>\n" +
            "</a>\n" +
            "<a class='icon--youtube nav__item nav__item--social' href='http://www.youtube.com/user/LonelyPlanet' itemprop='url'>\n" +
            "<span itemprop='name'>\n" +
            "Youtube\n" +
            "</span>\n" +
            "</a>\n" +
            "<a class='icon--pinterest nav__item nav__item--social' href='http://pinterest.com/lonelyplanet/' itemprop='url'>\n" +
            "<span itemprop='name'>\n" +
            "Pinterest\n" +
            "</span>\n" +
            "</a>\n" +
            "<a class='icon--instagram nav__item nav__item--social' href='http://instagram.com/lonelyplanet' itemprop='url'>\n" +
            "<span itemprop='name'>\n" +
            "Instagram\n" +
            "</span>\n" +
            "</a>\n" +
            "<a class='icon--vine nav__item nav__item--social' href='https://vine.co/lonelyplanet' itemprop='url'>\n" +
            "<span itemprop='name'>\n" +
            "Vine\n" +
            "</span>\n" +
            "</a>\n" +
            "<a class='icon--article nav__item nav__item--social' href='/blog' itemprop='url'>\n" +
            "<span itemprop='name'>\n" +
            "Article\n" +
            "</span>\n" +
            "</a>\n" +
            "</div>\n" +
            "</nav>\n" +
            "</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "<div class='row row--smallprint'>\n" +
            "<div class='row__inner row__inner--footer row--smallprint__inner'>\n" +
            "<div class='wv--split--left text--legal copy--disclaimer'>\n" +
            "<p>\n" +
            "&copy;\n" +
            "2016\n" +
            "Lonely Planet. All rights reserved. No part of this site may be reproduced without our written permission.\n" +
            "</p>\n" +
            "</div>\n" +
            "<div class='wv--split--right form--international'>\n" +
            "<div class='split--right__inner nav--inline form--language media'>\n" +
            "<label class='nav__item form--language__label title--footer media__img'>International</label>\n" +
            "<div class='media__body'>\n" +
            "<form action='//www.lonelyplanet.com/redirector'>\n" +
            "<div class='dropdown js-select-group-manager'>\n" +
            "<div aria-hidden class='dropdown__value js-select-overlay icon--chevron-down--after'>\n" +
            "English\n" +
            "</div>\n" +
            "<select class='dropdown__select js-select' data-form-submit='true' id='languageSelect' name='url'>\n" +
            "<option value='www.lonelyplanet.com'>English</option>\n" +
            "<option value='www.lonelyplanet.in'>English (India)</option>\n" +
            "<option value='www.lonelyplanet.de'>Deutsch</option>\n" +
            "<option value='www.lonelyplanet.fr'>Fran&#231;ais</option>\n" +
            "<option value='www.lonelyplanet.es'>Espa&#241;ol</option>\n" +
            "<option value='www.lonelyplanetitalia.it'>Italiano</option>\n" +
            "<option value='www.lonelyplanet.ru'>&#x420;&#x443;&#x441;&#x441;&#x43A;&#x438;&#x439;</option>\n" +
            "<option value='www.lonelyplanetbrasil.com.br'>Portugu&ecirc;s</option>\n" +
            "<option value='www.lonelyplanet.cz'>&#268;esky</option>\n" +
            "</select>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "<noscript>\n" +
            "<input class='btn btn--linkblue btn--medium' type='submit' value='Go'>\n" +
            "</noscript>\n" +
            "</form>\n" +
            "\n" +
            "</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "\n" +
            "</div>\n" +
            "<div class='js-config is-hidden'>\n" +
            "<script>\n" +
            "  (function(){\n" +
            "    if (window.lp.enhanced && \n" +
            "        window.lp.supports.localStorage && \n" +
            "        window.localStorage.getItem('_lpfont') != \"true\") {\n" +
            "  \n" +
            "      window.onload = function() {\n" +
            "        var xhr = new XMLHttpRequest(),\n" +
            "            fontPath = window.lp.supports.woff2 ? \"//assets.staticlp.com/assets/fonts_woff2-3bc47fd96afd0046e97b65fca74d8bdb.css\" : \"//assets.staticlp.com/assets/fonts-a01a8b56f0a1140ef270806c094f5c6b.css\";\n" +
            "  \n" +
            "        xhr.open('GET', fontPath);\n" +
            "  \n" +
            "        xhr.onload = function (e) {\n" +
            "          window.localStorage.setItem('_lpfont', 'true')\n" +
            "        };\n" +
            "  \n" +
            "        xhr.send();\n" +
            "      }\n" +
            "  \n" +
            "    }\n" +
            "  })()\n" +
            "</script>\n" +
            "\n" +
            "<script>\n" +
            "  window.lp.fs.log(function(){\n" +
            "    return {\n" +
            "      url: window.location.href,\n" +
            "      referrer: document.referrer,\n" +
            "      viewport: document.documentElement.clientWidth,\n" +
            "      variant: window.lp.getCookie(\"_v\")\n" +
            "    };\n" +
            "  }());\n" +
            "</script>\n" +
            "\n" +
            "<script>\n" +
            "  if (!window.lp.enhanced) {\n" +
            "    (function() {\n" +
            "  \n" +
            "      var firstScript = document.getElementsByTagName(\"script\")[0],\n" +
            "          js = document.createElement(\"script\");\n" +
            "  \n" +
            "      js.src = \"//assets.staticlp.com/assets/omniture/s_code-6586ca7d72a8d0a506d1cbc4f995706b.js\";\n" +
            "  \n" +
            "      js.onload = function(){\n" +
            "        var property;\n" +
            "        if (window.s && window.lp.tracking) {\n" +
            "          for (property in window.lp.tracking) {\n" +
            "            window.s[property] = window.lp.tracking[property];\n" +
            "          }\n" +
            "          if (typeof window.s.t === 'function') {\n" +
            "            window.s.t();\n" +
            "          }\n" +
            "        }\n" +
            "      };\n" +
            "  \n" +
            "      firstScript.parentNode.insertBefore(js, firstScript);\n" +
            "    }());\n" +
            "  }\n" +
            "</script>\n" +
            "\n" +
            "\n" +
            "<script>\n" +
            "//<![CDATA[\n" +
            "function extend(a,b){a=a||{};for(var c in b)\"object\"==typeof b[c]&&null!==b[c]&&b[c].constructor==Array?a[c]=b[c]:\"object\"==typeof b[c]?a[c]=extend(a[c],b[c]):a[c]=b[c];return a}window.lp = window.lp || {}; window.lp.ads=window.lp.ads||{}; extend(window.lp.ads, {\"layers\":[\"2009.lonelyplanet\",\"destinations\",\"asia\",\"vietnam\",\"central-vietnam\"],\"interest\":\"architecture-and-buildings,diving-and-snorkelling,food-and-drink,history-heritage-and-tradition,shopping\",\"template\":\"things-to-do-detail\",\"topic\":\"things-to-do\",\"keyValues\":{},\"adTnm\":\"poi-detail,sights-poi\",\"continent\":\"asia\",\"country\":\"vietnam\",\"destination\":\"hoi-an\"}); window.lp.tracking=window.lp.tracking||{}; extend(window.lp.tracking, {\"currencyCode\":\"USD\",\"eVar3\":\"Asia\",\"eVar4\":\"Vietnam\",\"eVar5\":\"Hoi An\",\"eVar11\":\"canary\",\"eVar12\":\"dest things to do\",\"eVar13\":\"Sights\",\"eVar35\":\"\",\"eVar37\":\"Asia : Southeast Asia : Vietnam, Cambodia, Laos \\u0026 Northern Thailand : Vietnam : Hue, Hoi An \\u0026 Central Vietnam : Central Vietnam : Hoi An\",\"eVar38\":\"USD\",\"oun\":\"lonelyplanet-global\",\"prop1\":\"Asia\",\"prop2\":\"Vietnam\",\"prop3\":\"Hoi An\",\"prop7\":\"dest things to do\",\"prop8\":\"Sights\",\"prop32\":\"Asia : Southeast Asia : Vietnam, Cambodia, Laos \\u0026 Northern Thailand : Vietnam : Hue, Hoi An \\u0026 Central Vietnam : Central Vietnam : Hoi An\",\"prop33\":\"USD\",\"un\":\"lonelyplanet-global\",\"pageName\":\"dest : Asia : Vietnam : Hoi An : sights\",\"channel\":\"dest\",\"prop9\":\"dest sights details\",\"prop43\":\"Lonely Planet\",\"prop46\":\"dest things to do\",\"eVar2\":\"dest\",\"eVar47\":\"Lonely Planet\",\"eVar14\":\"dest sights details\",\"eVar17\":\"dest things to do\",\"list1\":[\"architecture-and-buildings\",\"diving-and-snorkelling\",\"food-and-drink\",\"history-heritage-and-tradition\",\"shopping\"],\"prop10\":\"Chinese All-Community Assembly Hall\",\"eVar15\":\"Chinese All-Community Assembly Hall\"});\n" +
            "//]]>\n" +
            "</script>\n" +
            "<!-- Twitter -->\n" +
            "<script src=\"//platform.twitter.com/oct.js\" type=\"text/javascript\" onload=\"twttr.conversion.trackPid('l4vy8');\"></script>\n" +
            "<noscript>\n" +
            "  <img height=\"1\" width=\"1\" style=\"display:none;\" alt=\"\" src=\"https://analytics.twitter.com/i/adsct?txn_id=l4vy8&p_id=Twitter\" />\n" +
            "  <img height=\"1\" width=\"1\" style=\"display:none;\" alt=\"\" src=\"//t.co/i/adsct?txn_id=l4vy8&p_id=Twitter\" /></noscript>\n" +
            "\n" +
            "<!-- Facebook -->\n" +
            "<script>(function() {\n" +
            "  var _fbq = window._fbq || (window._fbq = []);\n" +
            "  if (!_fbq.loaded) {\n" +
            "    var fbds = document.createElement('script');\n" +
            "    fbds.async = true;\n" +
            "    fbds.src = '//connect.facebook.net/en_US/fbds.js';\n" +
            "    var s = document.getElementsByTagName('script')[0];\n" +
            "    s.parentNode.insertBefore(fbds, s);\n" +
            "    _fbq.loaded = true;\n" +
            "  }\n" +
            "  _fbq.push(['addPixelId', '747768198628299']);\n" +
            "})();\n" +
            "window._fbq = window._fbq || [];\n" +
            "window._fbq.push(['track', 'PixelInitialized', {}]);\n" +
            "</script>\n" +
            "<noscript><img height=\"1\" width=\"1\" alt=\"\" style=\"display:none\" src=\"https://www.facebook.com/tr?id=747768198628299&amp;ev=PixelInitialized\" /></noscript>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "<div class='pointer-cover' id='js-pointer-cover'></div>\n" +
            "\n" +
            "\n" +
            "<!--\n" +
            "build: waldorf-production-canary-d72159502a34e52a63bbedb5e8d2ca94bc05680d\n" +
            "-->\n" +
            "</div>\n" +
            "</body>\n";
    public static void main(String[] args) {

//        Document doc = Jsoup.parse(html);
//        Elements elements=doc.select(".card--page__content");
//
//        for(Element element : elements){
//            System.out.println(element.text());
//        }

            String url = "//images-resrc.staticlp.com/S=W750M,H450M,U/O=85/http://media.lonelyplanet.com/a/g/hi/t/ac2a5615aa7cbb965c4bc868dac152d3-tan-ky-house.jpg";
            System.out.println(url.split("http:")[1]);
    }
}
