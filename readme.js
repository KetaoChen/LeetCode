title = $('[data-cy=question-title]').textContent.trim(); 
copy(`${title.replace('.', ' |')} | ${$('[diff]').textContent} | [Solution](backtrack/${encodeURIComponent(title.trim())})`)