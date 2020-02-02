title = $('[data-cy=question-title]').textContent.trim(); 
copy(`${title.replace('.', ' |')} | ${$('[diff]').textContent} | [Solution](Tree/${encodeURIComponent(title.trim())})`)