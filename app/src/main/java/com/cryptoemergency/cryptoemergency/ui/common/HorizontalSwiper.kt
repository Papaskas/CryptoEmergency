package com.cryptoemergency.cryptoemergency.ui.common

import androidx.annotation.FloatRange
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.TargetedFlingBehavior
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Горизонатальный свайпер
 *
 * @param size количество страниц в свайпере.
 * @param modifier модификатор.
 * @param initialPage Начальная страница, которая будет отображаться. Значение по умолчанию равно 0.
 * @param initialPageOffsetFraction Начальная часть смещения для начальной страницы. Должно быть
 * от -0,5 до 0,5. Значение по умолчанию равно 0f.
 * @param content Компонуемое содержимое, которое будет отображаться на каждой странице.
 * Которое принимает целое число (индекс страницы) в качестве параметра.
 * @param contentPadding добавляет отступы ко всему содержимому. Это добавит отступы для содержимого
 * после его обрезки, что невозможно с помощью параметра [modifier]. Вы можете использовать его
 *, чтобы добавить отступы перед первой страницей или после последней. Используйте [pageSpacing], чтобы добавить интервалы
 * между страницами.
 * @param pageSize Используется для изменения внешнего вида страниц внутри этого свайпера.
 * @param beyondViewportPageCount для создания и размещения страниц до и после списка видимых
 * страниц. Примечание: Имейте в виду, что использование большого значения для [beyondViewportPageCount] приведет к большому количеству ошибок.
 * страницы должны быть составлены, измерены и размещены, что противоречит цели использования отложенной загрузки.
 * Это следует использовать в качестве оптимизации для предварительной загрузки нескольких страниц до и после видимых
 *. Это не относится к страницам, автоматически составленным и размещенным программой предварительной выборки в
 * направлении прокрутки во время событий прокрутки.
 * @param pageSpacing - объем пространства, который будет использоваться для разделения страниц в этом свайпере
 * @param verticalAlignment Определяет вертикальное выравнивание страниц в этом свайпере.
 * @param flingBehavior - Параметр [TargetedFlingBehavior], который будет использоваться для жестов прокрутки после публикации.
 * @param userScrollEnabled Определяет, разрешена ли прокрутка с помощью пользовательских жестов или специальных возможностей
 *. Вы по-прежнему можете выполнять прокрутку программно, используя [PagerState.scroll], даже если она
отключена.
 * @param reverseLayout изменяет направление прокрутки и расположение.
 * @param key - стабильный и уникальный ключ, представляющий элемент. Когда вы указываете клавишу, положение прокрутки
 * будет сохраняться в зависимости от клавиши, что означает, что если вы добавляете/удаляете элементы до того, как
 * текущий видимый элемент элемент с заданным ключом будет сохранен в качестве первого видимого элемента. Если указано значение null
 *, позиция в списке будет соответствовать ключу.
 * @param swiperNestedScrollConnection - Это [Вложенное соединение прокрутки], которое определяет, как этот [HorizontalSwiper]
 * ведет себя с вложенными списками. По умолчанию [HorizontalSwiper] будет использовать все вложенные значения delta.
 * @param snapPosition - расчет того, как этот свайпер будет выполнять привязку страниц.
 * Используйте это, чтобы обеспечить различное расположение в разных позициях макета. Это используется
 * [HorizontalSwiper] чтобы рассчитать [PagerState.currentPage], текущая страница - это страница, ближайшая
 * к позиции привязки в макете (например, если позиция привязки является началом макета, то
 * Текущая страница будет ближайшей к этой странице).
 *
 * @see androidx.compose.foundation.pager
 *
 * @sample SampleSwiper
 * @sample SampleSwiperWithList
 * @sample SampleSwiperWithComposableFunc
 */
@Composable
fun HorizontalSwiper(
    size: Int,
    modifier: Modifier = Modifier,
    pageSpacing: Dp = 0.dp,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    pageSize: PageSize = PageSize.Fill,
    beyondViewportPageCount: Int = PagerDefaults.BeyondViewportPageCount,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    userScrollEnabled: Boolean = true,
    reverseLayout: Boolean = false,
    key: ((index: Int) -> Any)? = null,
    snapPosition: SnapPosition = SnapPosition.Start,
    initialPage: Int = 0,
    @FloatRange(from = -0.5, to = 0.5) initialPageOffsetFraction: Float = 0f,
    state: PagerState = rememberPagerState(
        initialPage,
        initialPageOffsetFraction,
    ) { size },
    swiperNestedScrollConnection: NestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
        state,
        Orientation.Horizontal
    ),
    flingBehavior: TargetedFlingBehavior = PagerDefaults.flingBehavior(state = state),
    content: @Composable PagerScope.(Int) -> Unit,
) {
    HorizontalPager(
        contentPadding = contentPadding,
        pageSize = pageSize,
        beyondViewportPageCount = beyondViewportPageCount,
        verticalAlignment = verticalAlignment,
        userScrollEnabled = userScrollEnabled,
        reverseLayout = reverseLayout,
        key = key,
        snapPosition = snapPosition,
        pageNestedScrollConnection = swiperNestedScrollConnection,
        flingBehavior = flingBehavior,
        modifier = modifier,
        state = state,
        pageSpacing = pageSpacing,
        pageContent = content,
    )
}

@Composable
private fun SampleSwiper() {
    HorizontalSwiper(size = 5) { index ->
        Text(text = "Page $index")
    }
}

@Composable
private fun SampleSwiperWithList() {
    val items = listOf("asdasd", "ASdasd")

    HorizontalSwiper(items.size) { index ->
        Text(text = items[index])
    }
}

@Composable
private fun SampleSwiperWithComposableFunc() {
    val items = listOf<@Composable () -> Unit>(
        { Text("AAAAA") },
        { Text("BBBB") },
    )

    HorizontalSwiper(items.size) { index ->
        items[index]
    }
}
