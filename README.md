# weather
This project is used in the Treehouse workshop on Unit Testing a Spring App. The application is a Spring web app that uses two Google APIs and the forecast.io API.

## Getting Your API Keys
In order to use this application, you'll need to get a couple API keys. Here's how.
### Forecast.io
Go to https://developer.forecast.io/register and register with your email address. After registering, you'll see a page with an example URL that you can use to GET JSON data at an arbitrary latitude and longitude. Clicking that link will show you what the data looks like that our application will receive.

At the bottom of the page, you'll see your unique API key displayed. Copy and paste this into *api.properties* as the value for the property named `weather.api.key`.
### Google
While logged in to a Google account, navigate to https://console.developers.google.com. From the menu, choose **Credentials**, click **Create credentials**, and select **API key**. When asked, choose **Server key**. At this point you'll see your key, which you should copy. Paste this key into *api.properties* as the values for the `geocode.api.key` and `places.api.key` properties.

At this point you need to enable the Geocoding and Places API, so that your API key grants you access to those APIs. Under the menu item **Overview**, you should see a categorized list of Google APIs. Under the **Google Maps** category, enable the following APIs:
 
  - Google Maps Geocoding API, and
  - Google Places API Web Service

## Tags
This repository is tagged for the beginning of videos to help you follow progress through the workshop. Use the `git checkout` command to update your code. For example, to update your local repository to match the workshop code at the beginning of *Video 4*, use

`git checkout -f v4`

Keep in mind that this will overwrite any local changes you've made since your last checkout or commit.