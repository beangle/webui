<div id="${tag.id}" class="${tag.cssClass}" ${tag.parameterString}>
  <div class="card-header border-0 ui-sortable-handle" style="cursor: move;">
    <h3 class="card-title">${tag.title}</h3>
    <div class="card-tools">
      [#if tag.minimal!"--" == "true"
      <button type="button" class="btn bg-info btn-sm" data-card-widget="collapse">
        <i class="fas fa-minus"></i>
      </button>
      [/#if]
      [#if tag.closeable!"--" == "true"]
      <button type="button" class="btn bg-info btn-sm" data-card-widget="remove">
        <i class="fas fa-times"></i>
      </button>
      [/#if]
    </div>
  </div>
  <div class="card-body">
  ${tag.body}
  </div>
</div>
