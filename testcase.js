$('.source-content') && $('.source-content').remove(); // For problem in contest, there is a hidden `.source-content`. Ignore it.
copy($$('strong, b').filter(x => x.innerText.trim() === "Input:").map(x => x.nextSibling.textContent.split('\n')[0].trim()).map(x => x.replace(/(\,\ )?\w+\ =\ /g, '\n').trim()).join('\n'))
