/**
  * 换肤JS
  *
  * ZHULI 2013-03-22
  */


// 添加皮肤
function AddCSSLink(id, url)
{
    var doc = document;
    var link = doc.createElement("link");
    link.id = id;
    link.setAttribute("rel", "stylesheet");
    link.setAttribute("type", "text/css");
    link.setAttribute("href", url);

    var heads = doc.getElementsByTagName("head");
    if (heads.length)
        heads[0].appendChild(link);
    else
        doc.documentElement.appendChild(link);

	var $iframe = $('iframe');

	var id_link = "#" + id;

	// 设置每一个iframe的皮肤
	if ($iframe.length > 0)
	{
		for (var i = 0; i < $iframe.length; i++)
		{
			var ifr = $iframe[i];
			$(ifr).contents().find('' + id_link).attr('href', url);

			// 设置下一级每一个iframe的皮肤
			//$(ifr).contents().find('iframe').contents().find('' + id_link).attr('href', url);
			var $iframe2 = $(ifr).contents().find('iframe');
			if ($iframe2.length > 0)
			{
				for (var i2 = 0; i2 < $iframe2.length; i2++)
				{
					var ifr2 = $iframe2[i2];
					$(ifr2).contents().find('' + id_link).attr('href', url);

					// 下一级
					var $iframe3 = $(ifr2).contents().find('iframe');
					if ($iframe3.length > 0)
					{
						for (var i3 = 0; i3 < $iframe3.length; i3++)
						{
							var ifr3 = $iframe3[i3];
							$(ifr3).contents().find('' + id_link).attr('href', url);
						}
					}
				}
			}
		}
	}
}



